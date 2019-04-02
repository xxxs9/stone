package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.DepotInventoryCheckMapper;
import com.gameloft9.demo.dataaccess.model.system.DepotInventoryCheck;
import com.gameloft9.demo.dataaccess.model.system.DepotOrder;
import com.gameloft9.demo.dataaccess.model.system.SysDepot;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.DepotInventoryCheckService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.Constants;
import com.gameloft9.demo.utils.DateUtil;
import com.gameloft9.demo.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Sxiu
 * @create: 2019/3/30 15:39
 * @description:
 */
@Slf4j
@Service
@Transactional
public class DepotInventoryCheckServiceImpl implements DepotInventoryCheckService {

    @Autowired
    private DepotInventoryCheckMapper depotInventoryCheckMapper;
    /**
     * 获取盘点单列表
     * @param page                  页序
     * @param limit                 分页大小
     * @param sourceUser            发起人
     * @param checkType             盘点类型
     * @param checkState            状态盘点中/结束
     */
    @Override
    public List<DepotInventoryCheck> getAll(String page, String limit, String sourceUser, String checkType, String checkState) {
        PageRange pageRange = new PageRange(page, limit);
        return depotInventoryCheckMapper.getAll(pageRange.getStart(),pageRange.getEnd(),sourceUser,checkType,checkState);
    }

    /**
     * 获取盘点单列表大小
     * @param sourceUser            发起人
     * @param checkType             盘点类型
     * @param checkState            状态盘点中/结束
     * */
    @Override
    public int countGetAll(String sourceUser, String checkType, String checkState) {
        return depotInventoryCheckMapper.countGetAll(sourceUser,checkType,checkState);
    }

    /**
     * 添加盘点单
     * @param checkType           盘点类型
     * @param sourceUser          发起人
     * @param recordNumber        记录数量
     * */
    @Override
    public String addDepotInventoryCheck(String checkType, String sourceUser, String recordNumber) {

        CheckUtil.notBlank(checkType, "盘点类型为空");
        CheckUtil.notBlank(sourceUser, "发起人为空");
        CheckUtil.notBlank(recordNumber, "记录数量为空");

        DepotInventoryCheck depotInventoryCheck = new DepotInventoryCheck();
        depotInventoryCheck.setId(UUIDUtil.getUUID());
        depotInventoryCheck.setCheckType(checkType);
        depotInventoryCheck.setSourceUser(sourceUser);
        depotInventoryCheck.setSourceTime(DateUtil.date2Str(new Date()));
        depotInventoryCheck.setRecordNumber(recordNumber);
        depotInventoryCheck.setCheckState(Constants.DepotInventoryCheck.CHECK_IN);

        depotInventoryCheckMapper.insertSelective(depotInventoryCheck);
        return depotInventoryCheck.getId();
    }

    /**
     * 根据主键获取盘点单信息
     * @param id 盘点单主键
     * */
    @Override
    public DepotInventoryCheck getById(String id) {
        return depotInventoryCheckMapper.getById(id);
    }

    /**
     * 根据主键删除盘点单信息
     * @param id 盘点单id
     * */
    @Override
    public Boolean deleteDepotInventoryCheck(String id) {
        CheckUtil.notBlank(id, "盘点单id为空");
        depotInventoryCheckMapper.deleteByPrimaryKey(id);
        return true;
    }

    /**
     * 根据主键批量删除盘点单信息
     * @param ids 盘点单ids
     * */
    @Override
    public Boolean delsDepotInventoryCheck(String ids) {
        CheckUtil.notBlank(ids, "盘点单ids为空");
        List<String> depotInventoryCheckIds = new ArrayList<String>();
        String[] split = ids.split(",");
        for (String s : split) {
            depotInventoryCheckIds.add(s);
        }
        depotInventoryCheckMapper.delsByIds(depotInventoryCheckIds);
        return true;
    }


    /**
     * 结束盘点单
     * @param id                  盘点单id
     * */
    @Override
    public Boolean endDepotInventoryCheck(String id) {
        CheckUtil.notBlank(id, "盘点单id为空");

        DepotInventoryCheck depotInventoryCheck = new DepotInventoryCheck();
        depotInventoryCheck.setId(id);
        depotInventoryCheck.setCheckState(Constants.DepotInventoryCheck.CHECK_OUT);
        depotInventoryCheck.setState(Constants.DepotInventoryCheck.CHECK_AUIDT_IN);

        depotInventoryCheckMapper.updateByPrimaryKeySelective(depotInventoryCheck);

        return true;
    }

    /**
     * 审核通过,更新盘点单
     * @param id                  盘点单id
     * @param state               盘点单状态
     * */
    @Override
    public Boolean audit(String id, String state) {

        CheckUtil.notBlank(id, "盘点单id");

        DepotInventoryCheck depotInventoryCheck = new DepotInventoryCheck();
        depotInventoryCheck.setId(id);
        if(depotInventoryCheckMapper.getById(id).getState().equals(Constants.DepotInventoryCheck.CHECK_AUIDT_IN)){
            if(state.equals(Constants.DepotInventoryCheck.CHECK_PASS)){
                depotInventoryCheck.setState(Constants.DepotInventoryCheck.CHECK_PASS);
            }else{
                CheckUtil.notBlank(null, "未传入正确的状态信息");
            }
        }else {
            CheckUtil.notBlank(null, "已审核通过或已驳回");
        }

        depotInventoryCheckMapper.updateByPrimaryKeySelective(depotInventoryCheck);
        return true;
    }

    @Override
    public Boolean auditReject(String id, String state) {

        CheckUtil.notBlank(id, "盘点单id");

        DepotInventoryCheck depotInventoryCheck = new DepotInventoryCheck();
        depotInventoryCheck.setId(id);
        if(depotInventoryCheckMapper.getById(id).getState().equals(Constants.DepotInventoryCheck.CHECK_AUIDT_IN)){
            if(state.equals(Constants.DepotInventoryCheck.CHECK_FAIL)){
                depotInventoryCheck.setState(Constants.DepotInventoryCheck.CHECK_FAIL);
            }else{
                CheckUtil.notBlank(null, "未传入正确的状态信息");
            }
        }else {
            CheckUtil.notBlank(null, "已审核通过或已驳回");
        }

        depotInventoryCheckMapper.updateByPrimaryKeySelective(depotInventoryCheck);
        return true;
    }
}
