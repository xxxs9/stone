package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.SysDepotMapper;
import com.gameloft9.demo.dataaccess.model.system.SysDepot;
import com.gameloft9.demo.dataaccess.model.system.SysMenuTest;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.SysDepotService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SysDepotServiceImpl implements SysDepotService {

    @Autowired
    SysDepotMapper sysDepotMapper;


    /**
     * 获取仓库列表
     * @param page                  页序
     * @param limit                 分页大小
     * @param depotNumber           仓库编号
     * @param depotName             仓库名称
     * @param depotType             仓库类型
     */
    @Override
    public List<SysDepot> getAll(String page, String limit,String depotNumber,String depotName,String depotType) {
        PageRange pageRange = new PageRange(page, limit);
        return sysDepotMapper.getAll(pageRange.getStart(),pageRange.getEnd(),depotNumber,depotName,depotType);
    }

    /**
     * 添加仓库种类
     * @param depotNumber           仓库编号
     * @param depotName             仓库名
     * @param depotType             仓库类型
     * @param depotAddress          仓库地址
     * @param depotDescribe         仓库描述
     * */
    @Override
    public String addDepot( String depotNumber,String depotName, String depotType, String depotAddress, String depotDescribe) {

        CheckUtil.notBlank(depotName, "仓库名称为空");
        CheckUtil.notBlank(depotAddress, "仓库地址为空");

        //仓库名称不能重复
        SysDepot menuTest = sysDepotMapper.getByDepotName(depotName);
        CheckUtil.check(menuTest == null, "该仓库名称已经存在");

        SysDepot depot = new SysDepot();
        depot.setId(UUIDUtil.getUUID());
        depot.setDepotNumber(depotNumber);
        depot.setDepotName(depotName);
        depot.setDepotType(depotType);
        depot.setDepotAddress(depotAddress);
        depot.setDepotDescribe(depotDescribe);

        sysDepotMapper.insertSelective(depot);

        return depot.getId();
    }

    /**
     * 根据主键获取仓库信息
     * @param id 原料主键
     * */
    @Override
    public SysDepot getById(String id) {
        return sysDepotMapper.getById(id);
    }


    /**
     * 更新仓库种类
     * @param id                    仓库id
     * @param depotNumber           仓库编号
     * @param depotName             仓库名
     * @param depotType             仓库类型
     * @param depotAddress          仓库地址
     * @param depotDescribe         仓库描述
     * */
    @Override
    public Boolean updateDepot(String id, String depotNumber,String depotName, String depotType, String depotAddress, String depotDescribe) {

        CheckUtil.notBlank(id, "仓库id为空");
        CheckUtil.notBlank(depotName, "仓库名称为空");
        CheckUtil.notBlank(depotAddress, "仓库地址为空");

        //仓库名称不能重复
        SysDepot menuTest = sysDepotMapper.getByDepotName(depotName);
        CheckUtil.check(menuTest == null, "该仓库名称已经存在");

        SysDepot depot = new SysDepot();
        depot.setId(id);
        depot.setDepotNumber(depotNumber);
        depot.setDepotName(depotName);
        depot.setDepotType(depotType);
        depot.setDepotAddress(depotAddress);
        depot.setDepotDescribe(depotDescribe);

        sysDepotMapper.updateByPrimaryKeySelective(depot);

        return true;
    }

    /**
     * 删除仓库
     * @param id 仓库id
     * */
    @Override
    public Boolean deleteDepot(String id) {
        CheckUtil.notBlank(id, "仓库id为空");
        return true;
    }
}
