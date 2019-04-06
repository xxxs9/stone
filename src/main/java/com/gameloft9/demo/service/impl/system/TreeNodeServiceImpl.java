package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.SysMenuRoleTestMapper;
import com.gameloft9.demo.dataaccess.dao.system.SysMenuTestMapper;
import com.gameloft9.demo.dataaccess.dao.system.TreeNodeMapper;
import com.gameloft9.demo.dataaccess.model.system.Perms;
import com.gameloft9.demo.dataaccess.model.system.SysMenuRoleTest;
import com.gameloft9.demo.dataaccess.model.system.SysMenuTest;
import com.gameloft9.demo.dataaccess.model.system.TreeNode;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.TreeNodeService;
import com.gameloft9.demo.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/04/03 2019-04-03
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TreeNodeServiceImpl implements TreeNodeService {

    @Autowired
    TreeNodeMapper treeNodeMapper;
    @Autowired
    SysMenuRoleTestMapper sysMenuRoleTestMapper;
    @Autowired
    SysMenuTestMapper menuTestMapper;
    @Autowired
    SysMenuRoleTestMapper menuRoleTestMapper;

    @Override
    public List<TreeNode> getAllNode() {
        return treeNodeMapper.getAllNode();
    }

    @Override
    public List<TreeNode> initBelongTo() {
        return treeNodeMapper.initBelongTo();
    }

    @Override
    public String addPerms(Perms perms) {

        //角色列表
        List<String> roleIdList = perms.getRoleIdList();

        //添加节点
        TreeNode treeNode = new TreeNode();
        treeNode.setId(UUIDUtil.getUUID());
        treeNode.setParentId(perms.getParentId());
        treeNode.setBelongToName(perms.getBelongToName());
        treeNode.setName(perms.getName());
        treeNode.setPerms(perms.getPerms());
        treeNodeMapper.addPerms(treeNode);

        for (String roleId : roleIdList) {
            SysMenuRoleTest menuRoleTest = new SysMenuRoleTest();
            menuRoleTest.setId(UUIDUtil.getUUID());
            menuRoleTest.setMenuId(treeNode.getId());
            menuRoleTest.setRoleId(roleId);
            menuRoleTest.setCreateTime(new Date());
            //添加menu_role
            sysMenuRoleTestMapper.insert(menuRoleTest);
        }



        //添加资源
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String createUser = (String) request.getSession().getAttribute("sysUser");
        SysMenuTest sysMenuTest = new SysMenuTest();
        sysMenuTest.setId(treeNode.getId());
        sysMenuTest.setTitle(treeNode.getName());
        sysMenuTest.setHref("#");
        sysMenuTest.setRequestUrl(UUIDUtil.getUUID());
        sysMenuTest.setIcon("icon-text");
        sysMenuTest.setCode("");
        sysMenuTest.setParentId(treeNode.getParentId());
        sysMenuTest.setCreateUser(createUser);
        sysMenuTest.setCreateTime(new Date());
        sysMenuTest.setPerms(perms.getPerms());
        menuTestMapper.insert(sysMenuTest);

        return treeNode.getId();
    }

    @Override
    public Perms getById(String id) {
        TreeNode treeNode = treeNodeMapper.getById(id);

        Perms perms = new Perms();
        if(treeNode != null){
            perms.setId(treeNode.getId());
            perms.setParentId(treeNode.getParentId());
            perms.setBelongToName(treeNode.getBelongToName());
            perms.setName(treeNode.getName());
            perms.setPerms(treeNode.getPerms());
            perms.setRoleIdList(menuRoleTestMapper.getRoleIdsByMenuId(id));
        }
        return  perms;
    }

    /**
     * 更新菜单角色及权限
     */
    private void updateMenuRole(String menuId, List<String> idList) {
        // 先清空原来的
        clearMenuRole(menuId);

        //新增或者更新菜单角色表
        for (String roleId : idList) {
            SysMenuRoleTest menuRoleTest = new SysMenuRoleTest();
            menuRoleTest.setMenuId(menuId);
            menuRoleTest.setRoleId(roleId);
            //有则更新，没有则新增
            insertOrUpdateMenuRole(menuRoleTest);
        }
    }
    /**
     * 清空菜单角色关联关系
     *
     * @param menuId
     */
    private void clearMenuRole(String menuId) {
        sysMenuRoleTestMapper.deleteByMenuId(menuId);
    }

    /**
     * 新增或更新菜单角色
     *
     * @param sysMenuRoleTest 菜单角色对象
     */
    private int insertOrUpdateMenuRole(SysMenuRoleTest sysMenuRoleTest) {
        CheckUtil.notNull(sysMenuRoleTest, "菜单角色对象为空");
        CheckUtil.notBlank(sysMenuRoleTest.getMenuId(), "菜单id为空");
        CheckUtil.notBlank(sysMenuRoleTest.getRoleId(), "角色id为空");

        SysMenuRoleTest oldMenu = sysMenuRoleTestMapper.selectByMenuIdAndRoleId(sysMenuRoleTest.getMenuId(), sysMenuRoleTest.getRoleId());
        if (null == oldMenu) {
            log.info("开始新增菜单角色关联记录");
            sysMenuRoleTest.setId(UUIDUtil.getUUID());
            return sysMenuRoleTestMapper.insertSelective(sysMenuRoleTest);
        }

        log.info("开始更新菜单角色关联记录");
        sysMenuRoleTest.setId(oldMenu.getId());
        return sysMenuRoleTestMapper.updateByPrimaryKeySelective(sysMenuRoleTest);
    }

    @Override
    public Boolean updatePerms(String id, String belongToName, String name, String perms, String parentId, List<String> roleIdList) {
        CheckUtil.notBlank(name, "资源名称为空");
        CheckUtil.notBlank(perms, "资源代号为空");
        //更新treeNode
        TreeNode treeNode = new TreeNode();
        treeNode.setId(id);
        treeNode.setPerms(perms);
        treeNode.setName(name);
        treeNodeMapper.updatePerms(treeNode);

        //更新菜单角色设置
        updateMenuRole(id, roleIdList);

        return true;
    }

    @Override
    public Boolean deletePerms(String id) {

        //删除nodeTree
        treeNodeMapper.deletePerms(id);
        //删除menu_role
        menuRoleTestMapper.deleteByMenuId(id);
        //删除menu
        menuTestMapper.deleteByPrimaryKey(id);

        return true;
    }


}
