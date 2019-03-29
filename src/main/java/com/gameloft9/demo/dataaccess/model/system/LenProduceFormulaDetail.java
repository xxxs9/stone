package com.gameloft9.demo.dataaccess.model.system;

import com.gameloft9.demo.utils.lennonUtils.LenSuperClass;
import lombok.Data;

/**
 * @author: Lennon_Yuan
 * @time: 2019/3/18 0018-下午 2:25
 * @description: 生产配方明细
 */
@Data
public class LenProduceFormulaDetail {
    private String id;
    private String produceFormulaId;
    private String materialId;
    private String materialNumber;
    /**
     * 仓库编号
     */
    private String depotId;
    private String other1;
    private String other2;
    private String other3;

}
