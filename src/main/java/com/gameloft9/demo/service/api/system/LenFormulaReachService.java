package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.LenFormulaReach;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-23 - 17:40
 * @description :
 */

public interface LenFormulaReachService {

    /**
     *查找所有（无条件）
     *
     * @return
     *
     */
    List<LenFormulaReach> selectAll();

    /**
     * 按照主键查找所有
     *
     * @param id
     * @return
     */
    LenFormulaReach getByPrimaryKey(String id);

    /**
     *
     * 分页
     *
     * @param page
     * @param limit
     * @param reachUser
     * @param productId
     * @return
     */
    List<LenFormulaReach> selectByPage(
            String page,String limit,String productId,
            String reachUser

    );

    /**
     * 增加
     *
     * @param id
     * @param productId
     * @param productFormulaId
     * @param ProductFormulaDetailId
     * @param depotAudi
     * @param formulaBack
     * @param state
     * @param reachUser
     * @param reachTime
     * @return
     */
    boolean insert(String id,String productId,String productFormulaId,String ProductFormulaDetailId,
               String depotAudi,String formulaBack,String state,String reachUser,String reachTime ,String other1 );

    /**
     * 修改
     *
     * @param id
     * @param productId
     * @param productFormulaId
     * @param ProductFormulaDetailId
     * @param depotAudi
     * @param formulaBack
     * @param state
     * @param reachUser
     * @param reachTime
     * @return
     */
    boolean update(String id,String productId,String productFormulaId,String ProductFormulaDetailId,
               String depotAudi,String formulaBack,String state,String reachUser,String reachTime  );

    /**
     * 删除
     *
     * @param id
     * @return
     */
    boolean delete(String id);

    /**
     * 统计条数
     *
     * @param
     * @return
     */
    int dataCount();

    /**
     * 更改状态
     *
     * @param id
     * @return
     */
    boolean changeState(String id);




   /* boolean updateByPrimaryKeySelective(String id,String productId,String productFormulaId,String ProductFormulaDetailId,
                                    String depotAudi,String formulaBack,String state,String reachUser,String reachTime  );

*/
  /*  boolean insertSelective(String id,String productId,String productFormulaId,String ProductFormulaDetailId,
                        String depotAudi,String formulaBack,String state,String reachUser,String reachTime  );
*/

    boolean changeDepotAudi(String depot,String id);

    /**
     * 暂停生产
     */
    boolean stopProduct(String id);

    boolean goOn(String id);

    boolean complete(String id);

    /**
     * 通过productID查询
     * @param productId
     * @return
     */
    LenFormulaReach getByProductId (String productId);
}
