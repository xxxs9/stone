package com.gameloft9.demo.security;

import com.gameloft9.demo.dataaccess.dao.system.SysMenuTestMapper;
import com.gameloft9.demo.dataaccess.dao.system.SysUserRoleTestMapper;
import com.gameloft9.demo.dataaccess.model.system.SysMenuRoleTest;
import com.gameloft9.demo.dataaccess.model.system.SysMenuTest;
import com.gameloft9.demo.dataaccess.model.system.SysRoleTest;
import com.gameloft9.demo.dataaccess.model.system.UserTest;
import com.gameloft9.demo.service.api.system.SysUserService;
import com.gameloft9.demo.utils.CacheUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 认证授权。
 * @author gameloft9
 */
@Slf4j
@Data
public class ShiroRealm extends AuthorizingRealm{

	/**
	 * 通过setter注入,这里没有通过@Autowired注入
	 * */
	private SysUserService userServiceImpl;
	@Autowired
	SysUserRoleTestMapper userRoleTestMapper;
	@Autowired
	SysMenuTestMapper menuTestMapper;

	/**
	 * 获取授权信息方法，返回用户角色信息
	 * */
	public String[] getAnnotationValue(Annotation a) {
		RequiresPermissions rpAnnotation = (RequiresPermissions)a;
		return rpAnnotation.value();
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(

			PrincipalCollection principals) {
		if (principals == null) {
			throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
		}

		UserTest user = (UserTest) principals.getPrimaryPrincipal();
		List<String> cacheData = CacheUtil.getInstance().getCacheData(user.getLoginName());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		if (user != null) {
			info.setStringPermissions((new HashSet<String>(cacheData)));
		} else {
			SecurityUtils.getSubject().logout();
		}
		return info;
	}

	/**
	 * 重写回调认证方法，subject.login()调用后回调此方法，获取认证信息。
	 * 如果是与第三方用户系统集成，可在此处进行身份认证，成功后可构造一个同登录token一致的认证信息。
	 * 或者干脆跳过shiro的认证，自己实现认证逻辑，成功后将用户信息放入session、cookie.
	 * */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		UserTest user = userServiceImpl.getByLoginName(token.getUsername());
		if (user == null) {
			//用户不存在
			throw new UnknownAccountException();
		}
		//创建一个list存放权限集合
		List<String> perms = new ArrayList<String>();
		//根据用户获取角色列表
		List<SysRoleTest> roles = userRoleTestMapper.getRolesByUserId(user.getId());
		//循环获取权限
		for (SysRoleTest role : roles) {
			List<SysMenuRoleTest> menuRoles = menuTestMapper.getMenuRoleByRoleId(role.getId());
			for (SysMenuRoleTest menuRole : menuRoles) {
				//根据角色获取资源列表
				SysMenuTest menu = menuTestMapper.getMenuByMenuId(menuRole.getMenuId());
				//权限不为null并且不为空串才存
				if(menu.getPerms() != null && menu.getPerms() != ""){
					perms.add(menu.getPerms());
				}

			}
		}

		//将权限存放到map缓存中
		CacheUtil cacheUtil = CacheUtil.getInstance();
		cacheUtil.addCacheData(user.getLoginName(),perms);

		//构造一个用户认证信息并返回，后面会通过这个和token的pwd进行对比。
		return new SimpleAuthenticationInfo(user,user.getPassword(),user.getRealName());
	}
}
