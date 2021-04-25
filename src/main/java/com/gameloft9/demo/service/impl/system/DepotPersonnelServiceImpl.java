package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.DepotPersonnelMapper;
import com.gameloft9.demo.dataaccess.dao.system.UserMapper;
import com.gameloft9.demo.dataaccess.model.system.DepotPersonnel;
import com.gameloft9.demo.dataaccess.model.system.SysDepot;
import com.gameloft9.demo.dataaccess.model.system.SysUserRoleTest;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.DepotPersonnelService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.Constants;
import com.gameloft9.demo.utils.DateUtil;
import com.gameloft9.demo.utils.UUIDUtil;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Sxiu
 * @create: 2019/3/19 15:20
 * @description:
 */
@Slf4j
@Service
public class DepotPersonnelServiceImpl implements DepotPersonnelService {

    @Autowired
    private DepotPersonnelMapper depotPersonnelMapper;
    @Autowired
    private UserMapper userMapper;
    /**
     * 获取仓库人员列表
     * @param page                  页序
     * @param limit                 分页大小
     * @param depotNumber           仓库编号
     * @param userLoginName         用户登入名
     */
    @Override
    public List<DepotPersonnel> getAll(String page, String limit, String depotNumber, String userLoginName) {
        PageRange pageRange = new PageRange(page, limit);
        return depotPersonnelMapper.getAll(pageRange.getStart(),pageRange.getEnd(),depotNumber,userLoginName);
    }


    /**
     * 获取仓库人员列表大小
     * @param depotNumber           仓库编号
     * @param userLoginName         用户登入名
     * */
    @Override
    public int countGetAll(String depotNumber, String userLoginName) {
        return depotPersonnelMapper.countGetAll(depotNumber,userLoginName);
    }

    /**
     * 添加仓库人员
     * @param depotNumber           仓库编号
     * @param userLoginName         用户登入名
     * @param contactAddress        联系地址
     * @param birthday              出生日期
     * @param personPortrait        人员头像
     * */
    @Override
    public String addDepotPersonnel(String depotNumber, String userLoginName, String contactAddress, String birthday, String personPortrait) {

        CheckUtil.notBlank(depotNumber, "仓库编号为空");
        CheckUtil.notBlank(userLoginName, "用户登入名为空");

        //一个人员只能分配给一个仓库
        DepotPersonnel menuTest = depotPersonnelMapper.getByUserLoginName(userLoginName);
        CheckUtil.check(menuTest == null, "该人员已经被分配");

        DepotPersonnel depotPersonnel = new DepotPersonnel();
        depotPersonnel.setId(UUIDUtil.getUUID());
        depotPersonnel.setDepotNumber(depotNumber);
        depotPersonnel.setUserLoginName(userLoginName);
        depotPersonnel.setContactAddress(contactAddress);
        depotPersonnel.setBirthday(DateUtil.str2Date(birthday,"yyyy-MM-dd"));
        depotPersonnel.setPersonPortrait(personPortrait);

        depotPersonnelMapper.insertSelective(depotPersonnel);
        return depotPersonnel.getId();
    }


    /**
     * 根据主键获取仓库人员信息
     * @param id 仓库人员主键
     * */
    @Override
    public DepotPersonnel getById(String id) {
        return depotPersonnelMapper.getById(id);
    }

    @Override
    public Boolean updateDepotPersonnel(String id, String depotNumber, String userLoginName, String contactAddress, String birthday, String personPortrait) {

        CheckUtil.notBlank(id, "仓库人员id为空");
        CheckUtil.notBlank(depotNumber, "仓库编号为空");
        CheckUtil.notBlank(userLoginName, "用户登入名为空");

        DepotPersonnel depotPersonnel = new DepotPersonnel();
        depotPersonnel.setId(id);
        depotPersonnel.setDepotNumber(depotNumber);
        depotPersonnel.setUserLoginName(userLoginName);
        depotPersonnel.setContactAddress(contactAddress);
        depotPersonnel.setBirthday(DateUtil.str2Date(birthday,"yyyy-MM-dd"));
        depotPersonnel.setPersonPortrait(personPortrait);

        depotPersonnelMapper.updateByPrimaryKeySelective(depotPersonnel);
        return true;
    }

    /**
     * 删除仓库人员
     * @param id 仓库id
     * */
    @Override
    public Boolean deleteDepotPersonnel(String id) {
        CheckUtil.notBlank(id, "仓库人员id为空");
        depotPersonnelMapper.deleteByPrimaryKey(id);
        return true;
    }


    /**
     * 批量删除仓库人员
     * @param ids 仓库ids
     * */
    @Override
    public Boolean delsDepotPersonnel(String ids) {
        CheckUtil.notBlank(ids, "仓库人员ids为空");
        List<String> depotPersonnelIds = new ArrayList<String>();
        String[] split = ids.split(",");
        for (String s : split) {
            depotPersonnelIds.add(s);
        }
        depotPersonnelMapper.delsByIds(depotPersonnelIds);
        return true;
    }

    /**
     * 获取所有仓库人员的用户登入名
     * */
    @Override
    public List<String> getDepotUserLoginName() {
        return userMapper.getUserLoginNameByOrgName(Constants.DPOT_ORG);
    }
}
