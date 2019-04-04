package com.gameloft9.demo.dataaccess.dao.system;


import com.gameloft9.demo.dataaccess.model.system.LenFormulaReach;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-23 - 17:30
 * @description :
 */
public interface LenFormulaReachMapper {

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
     * 分页
     *
     * @param start
     * @param end
     * @param productId
     * @param reachUser
     * @return
     */
    List<LenFormulaReach> selectByPage(
            @Param("start") int start,
            @Param("end") int end,
            @Param("productId") String productId,
            @Param("reachUser") String reachUser

    );
/*
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
/*
    int insert(String id,String productId,String productFormulaId,String ProductFormulaDetailId,
               String depotAudi,String formulaBack,String state,String reachUser,String reachTime  );
*/

    int insert(LenFormulaReach lenFormulaReach);
   /* /**
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
   /*
    int update(String id,String productId,String productFormulaId,String ProductFormulaDetailId,
               String depotAudi,String formulaBack,String state,String reachUser,String reachTime  );
*/

   int update(LenFormulaReach lenFormulaReach);
    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delete(String id);

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
     * @param state
     * @param id
     * @return
     */
    int changeState(@Param("state") String state,@Param("id") String id);

    /**
     *
     * @param depot
     * @param id
     * @return
     */
    int changeDepot(@Param("depot")String depot,@Param("id")String id);

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
    int updateByPrimaryKeySelective(String id,String productId,String productFormulaId,String ProductFormulaDetailId,
                                    String depotAudi,String formulaBack,String state,String reachUser,String reachTime  );

    /**
     * 插入
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
    int insertSelective(String id,String productId,String productFormulaId,String ProductFormulaDetailId,
                        String depotAudi,String formulaBack,String state,String reachUser,String reachTime  );

    LenFormulaReach getByProductId (@Param("productId")String productId);
}
