package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.LenOperator;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.LenOperatorService;
import com.gameloft9.demo.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-04-11 - 22:39
 * @description :
 */
@Controller
@RequestMapping("/opera")
public class LenOperatorController {
    @Autowired
    LenOperatorService lenOperatorService;

    @RequestMapping("/pageList")
    @ResponseBody
    public IResult pageList(String page, String limit, String operatorType, String operatorUser){
        return new PageResultBean<List>(lenOperatorService.selectByPage(page, limit, operatorType, operatorUser),lenOperatorService.dataCount());
    }

    /**
     * 操作记录增加的方法
     * @param lenOperator
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public IResult add(LenOperator lenOperator){

                if(lenOperatorService.insertSelective(lenOperator)){
                    return  new ResultBean<Boolean>(true);
        }else {
                    return new ResultBean<String>("4011","操作记录出错");
                }
    }

    /**
     * 提交加工单
     * @param operatorUser
     * @param operatorRemark
     * @return
     */
    @RequestMapping("/prodDel")
    @ResponseBody
    public IResult prodDel(String operatorUser,String operatorRemark){

        lenOperatorService.insertSelective1(operatorUser, Constants.operatorState.PRODUCT_DEL,operatorRemark,null,null,null);
            return  new ResultBean<Boolean>(true);

    }
    @RequestMapping("/get")
    @ResponseBody
    public IResult getById(String id) {
        return new ResultBean<LenOperator>(lenOperatorService.getById(id));
    }

    /**
     * 提交加工单
     * @param operatorUser
     * @param operatorRemark
     * @return
     */
    @RequestMapping("/prodTJ")
    @ResponseBody
    public IResult prodTJ(String operatorUser,String operatorRemark){

        lenOperatorService.insertSelective1(operatorUser, Constants.operatorState.PRODUCT_TJ,operatorRemark,null,null,null);
        return  new ResultBean<Boolean>(true);

    }

    /**
     * 加工单审核的记录
     * @param operatorUser
     * @param operatorRemark
     * @return
     */
    @RequestMapping("/prodSH")
    @ResponseBody
    public IResult prodSH(String operatorUser,String operatorRemark){

        lenOperatorService.insertSelective1(operatorUser, Constants.operatorState.PRODUCT_SH,operatorRemark,null,null,null);
        return  new ResultBean<Boolean>(true);

    }
    @RequestMapping(value = "/prodSB",method = RequestMethod.POST)
    @ResponseBody
    public IResult prodSB(String operatorUser,String operatorRemark){

        lenOperatorService.insertSelective1(operatorUser, Constants.operatorState.PRODUCT_SB,operatorRemark,null,null,null);
        return  new ResultBean<Boolean>(true);

    }

    /**
     * 加工单修改记录
     * @param operatorUser
     * @param operatorRemark
     * @return
     */
    @RequestMapping(value = "/prodEdit",method = RequestMethod.POST)
    @ResponseBody
    public IResult prodEdit(String operatorUser,String operatorRemark){

        lenOperatorService.insertSelective1(operatorUser, Constants.operatorState.PRODUCT_UPD,operatorRemark,null,null,null);
        return  new ResultBean<Boolean>(true);

    }

}
