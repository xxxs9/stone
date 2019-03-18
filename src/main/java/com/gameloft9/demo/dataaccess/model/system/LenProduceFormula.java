package com.gameloft9.demo.dataaccess.model.system;

import com.gameloft9.demo.utils.lennonUtils.LenSuperClass;
import lombok.Data;

/**
 * @author: Lennon_Yuan
 * @time: 2019/3/18 0018-下午 2:20
 * @description: 生产配方
 */
@Data
public class LenProduceFormula extends LenSuperClass  {
    /***/
    private String id;
    private String productId;
    private String formulaType;
    private String formulaNumber;
    private String createUserId;

}
