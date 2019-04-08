package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.LenProduceFormulaDetailMapper;
import com.gameloft9.demo.dataaccess.model.system.LenProduceFormulaDetail;
import com.gameloft9.demo.service.api.system.LenProduceFormulaDetailService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.OrderUtil;
import com.gameloft9.demo.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @packageName: com.gameloft9.demo.service.impl.system
 * @author: Lennon_Yuan
 * @time: 2019/3/20 0020 - 上午 10:48
 * @description:
 */
@Service
public class LenProduceFormulaDetailServiceImpl implements LenProduceFormulaDetailService {

   @Autowired
    LenProduceFormulaDetailMapper mapper;
    /**
     * 查找所有（无条件）
     *
     * @return
     */
    public List<LenProduceFormulaDetail> selectAll() {
        return mapper.selectAll();
    }

    /**
     * 通过formulaId查找
     * @param formulaId
     * @return
     */
    public List<LenProduceFormulaDetail> getByFormulaId(String formulaId) {
        return mapper.getByFormulaId(formulaId);
    }

    /**
     * 按照主键查找所有
     *
     * @param id
     * @return
     */
    public LenProduceFormulaDetail getByPrimaryKey(String id) {
        return mapper.getByPrimaryKey(id);
    }

    /**
     * 分页查找
     *
     * @param page
     * @param limit
     * @param materialId
     * @param depotId
     * @return
     */
    public List<LenProduceFormulaDetail> selectByPage(String page, String limit, String materialId, String depotId) {
        PageRange pageRange = new PageRange(page, limit);
        return mapper.selectByPage(pageRange.getStart(), pageRange.getEnd(), materialId, depotId);

    }

    /**
     * 增加
     *
     * @param lenProduceFormulaDetail
     * @return
     */
    public boolean insert(LenProduceFormulaDetail lenProduceFormulaDetail) {
        String uuid = UUIDUtil.getUUID();
        LenProduceFormulaDetail len = new LenProduceFormulaDetail();
        len.setId(uuid);
        len.setDepotId(lenProduceFormulaDetail.getDepotId());
        len.setMaterialId(lenProduceFormulaDetail.getMaterialId());
        len.setMaterialNumber(lenProduceFormulaDetail.getMaterialNumber());
        len.setProduceFormulaId(lenProduceFormulaDetail.getProduceFormulaId());
        len.setOther1(OrderUtil.lenOrderNumber("DE"));
        if (mapper.insert(len) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 修改
     *
     * @param len
     * @return
     */
    public boolean update(LenProduceFormulaDetail len) {
        if (mapper.update(len) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean delete(String id) {
        if (mapper.delete(id) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查询条数
     *
     * @param depotId 仓库ID
     * @return
     */
    public int dataCount(String depotId) {
        return mapper.dataCount(depotId);
    }

    /**
     * 选择插入
     *
     * @param produceFormulaDetail
     * @return
     */
    public boolean insertSelective(LenProduceFormulaDetail produceFormulaDetail) {
        return false;
    }

    /**
     * 选择修改
     *
     * @param lenProduceFormulaDetail
     * @return
     */
    public boolean updateByPrimaryKeySelective(LenProduceFormulaDetail lenProduceFormulaDetail) {
        return false;
    }

    /**
     * 由formulaId找到formulaDetailID
     *
     * @param produceFormulaId
     * @return
     */
    @Override
    public String selectIdByFomulaId(String produceFormulaId) {
        return mapper.selectFomulaId(produceFormulaId);
    }
}
