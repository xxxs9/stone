package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.DepotInventoryCheckDetailMapper;
import com.gameloft9.demo.dataaccess.dao.system.DepotInventoryCheckMapper;
import com.gameloft9.demo.dataaccess.model.system.DepotInventoryCheck;
import com.gameloft9.demo.dataaccess.model.system.DepotInventoryCheckDetail;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.DepotInventoryCheckDetailService;
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
 * @create: 2019/3/31 14:18
 * @description:
 */
@Slf4j
@Service
@Transactional
public class DepotInventoryCheckDetailServiceImpl implements DepotInventoryCheckDetailService {


    @Autowired
    private DepotInventoryCheckDetailMapper depotInventoryCheckDetailMapper;

    /**
     * 获取盘点单明细记录数据
     * @param page                  页序
     * @param limit                 分页大小
     * @param checkId               盘点单ID
     */
    @Override
    public List<DepotInventoryCheckDetail> getAll(String page, String limit, String checkId) {
        PageRange pageRange = new PageRange(page, limit);
        return depotInventoryCheckDetailMapper.getAll(pageRange.getStart(),pageRange.getEnd(),checkId);
    }

    /**
     * 获取盘点单明细记录数据条数
     * @param checkId               盘点单ID
     * */
    @Override
    public int countGetAll(String checkId) {
        return depotInventoryCheckDetailMapper.countGetAll(checkId);
    }

    /**
     * 新增盘点单明细
     * @param checkId       盘点单ID
     * @param type          货品（原料/成品）
     * @param goodsId       原料/成品ID
     * @param goodsNumber   货品数量
     * */
    @Override
    public String addDepotInventoryCheckDetail(String checkId, String type, String goodsId, String goodsNumber) {

        CheckUtil.notBlank(checkId, "盘点单ID为空");
        CheckUtil.notBlank(type, "货物类型为空");
        CheckUtil.notBlank(goodsId, "货物编号为空");
        CheckUtil.notBlank(goodsNumber, "货品数量为空");

        DepotInventoryCheckDetail depotInventoryCheckDetail = new DepotInventoryCheckDetail();
        depotInventoryCheckDetail.setId(UUIDUtil.getUUID());
        depotInventoryCheckDetail.setCheckId(checkId);
        depotInventoryCheckDetail.setType(type);
        depotInventoryCheckDetail.setGoodsId(goodsId);
        depotInventoryCheckDetail.setGoodsNumber(goodsNumber);

        depotInventoryCheckDetailMapper.insertSelective(depotInventoryCheckDetail);
        return depotInventoryCheckDetail.getId();
    }

    /**
     * 根据主键获取盘点单明细信息
     * @param id 盘点单明细主键
     * */
    @Override
    public DepotInventoryCheckDetail getById(String id) {
        return depotInventoryCheckDetailMapper.getById(id);
    }

    /**
     * 更新盘点单明细
     * @param id            盘点单明细ID
     * @param checkUser     盘点人
     * @param checkNumber   盘点数量
     * */
    @Override
    public Boolean updateDepotInventoryCheckDetail(String id, String checkUser, String checkNumber) {

        CheckUtil.notBlank(id, "盘点单明细ID为空");
        CheckUtil.notBlank(checkUser, "盘点人为空");
        CheckUtil.notBlank(checkNumber, "盘点数量为空");

        DepotInventoryCheckDetail depotInventoryCheckDetail = new DepotInventoryCheckDetail();
        depotInventoryCheckDetail.setId(id);
        depotInventoryCheckDetail.setCheckUser(checkUser);
        depotInventoryCheckDetail.setCheckNumber(checkNumber);
        depotInventoryCheckDetail.setCheckTime(new Date());

        depotInventoryCheckDetailMapper.updateByPrimaryKeySelective(depotInventoryCheckDetail);

        return true;
    }

    /**
     * 根据主键删除盘点单明细信息
     * @param id 盘点单明细id
     * */
    @Override
    public Boolean deleteDepotInventoryCheckDetail(String id) {
        CheckUtil.notBlank(id, "盘点单明细id为空");
        depotInventoryCheckDetailMapper.deleteByPrimaryKey(id);
        return true;
    }

    /**
     * 根据主键批量删除盘点单明细信息
     * @param ids 盘点单明细ids
     * */
    @Override
    public Boolean delsDepotInventoryCheckDetail(String ids) {
        CheckUtil.notBlank(ids, "盘点单明细ids为空");
        List<String> depotInventoryCheckDetailIds = new ArrayList<String>();
        String[] split = ids.split(",");
        for (String s : split) {
            depotInventoryCheckDetailIds.add(s);
        }
        depotInventoryCheckDetailMapper.delsByIds(depotInventoryCheckDetailIds);
        return true;
    }
}
