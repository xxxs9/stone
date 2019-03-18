package com.gameloft9.demo.dataaccess.model.system;

import com.gameloft9.demo.utils.lennonUtils.LenSuperClass;
import lombok.Data;

/**
 * @author: Lennon_Yuan
 * @time: 2019/3/18 0018-下午 2:25
 * @description: 生产配方明细
 */
@Data
public class LenProduceFormulaDetail extends LenSuperClass {
    private String id;
    private String productFormulaId;
    private String materialId;
    private String materialNumber;
    /**
     * 仓库编号
     */
    private String depotId;

}
