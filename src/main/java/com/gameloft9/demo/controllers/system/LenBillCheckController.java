package com.gameloft9.demo.controllers.system;


import com.gameloft9.demo.dataaccess.model.system.LenBillCheck;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.LenBillCheckService;
import com.gameloft9.demo.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-30 - 01:11
 * @description :
 */
@Controller
@RequestMapping("/billCheck")
public class LenBillCheckController {
    @Autowired
    LenBillCheckService service;

   /* @RequestMapping(value = "/finalCheck")
    @ResponseBody
    public IResult finalCheck(String id, String productName, String state, String checkNumber, String checkDate, String checkUser, String checkRemark, String reachId, String productId, String planId){

    }*/

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAll() {
        return new ResultBean<List>(service.selectAll());
    }

    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAllByPage(String page, String limit, String productName, String state) {
        return new PageResultBean<List>(service.selectByPage(page, limit, productName, state), service.dataCount(state));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public IResult add(String id, String productName, String state, String checkNumber, String checkDate, String checkUser, String checkRemark, String reachId, String productId, String planId) {
        return new ResultBean<Boolean>(service.insert(id, productName, state, checkNumber, checkDate, checkUser, checkRemark, reachId, productId, planId));
    }

    @RequestMapping(value = "/producingAdd", method = RequestMethod.POST)
    @ResponseBody
    public IResult producingAdd(String id, String productName, String state, String checkNumber, String checkDate, String checkUser, String checkRemark, String reachId, String productId, String planId) {
        return new ResultBean<Boolean>(service.finalInsert(id, productName, state, checkNumber, checkDate, checkUser, checkRemark, reachId, productId, planId));
    }

    @RequestMapping(value = "/upd", method = RequestMethod.POST)
    @ResponseBody
    public IResult update(String id, String productName, String state, String checkNumber, String checkDate, String checkUser, String checkRemark, String reachId, String productId, String planId) {
        return new ResultBean<Boolean>(service.update(id, productName, state, checkNumber, checkDate, checkUser, checkRemark, reachId, productId, planId));
    }

    @RequestMapping("/del")
    @ResponseBody
    public IResult delete(String id) {
        if (service.delete(id)) {
            Boolean flag = true;
            return new ResultBean(flag);
        } else {
            return new ResultBean<String>("4011",">>>>>>>权限不足<<<<<<<");
        }

    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public IResult getById(String id) {
        return new ResultBean<LenBillCheck>(service.getByPrimaryKey(id));
    }

    /**
     * 存储验收合格单
     * @param id
     * @return
     */
    @RequestMapping(value = "/ok", method = RequestMethod.POST)
    @ResponseBody
    public IResult ok(String id) {

        if (service.changeState(Constants.productState.COMPLETE_PRODUCE, id)) {
            return new ResultBean<Boolean>(true);
        }
        return new ResultBean<String>("4011",">>>>>>>操作失败<<<<<<<");
    }

    @RequestMapping(value = "/notOk", method = RequestMethod.POST)
    @ResponseBody
    public IResult notOk(String id) {

        if (service.changeState(Constants.SCZT, id)) {
            return new ResultBean<Boolean>(true);
        }
        return new ResultBean<String>("4011",">>>>>>>操作失败<<<<<<<");
    }

    @RequestMapping(value = "/stepBack", method = RequestMethod.POST)
    @ResponseBody
    public IResult stepBack(String id) {

        if (service.changeState(Constants.UN_TIJIAO, id)) {
            return new ResultBean<Boolean>(true);
        }
        return new ResultBean<String>("4011",">>>>>>>操作失败<<<<<<<");
    }

    @RequestMapping(value = "/intoDepot", method = RequestMethod.POST)
    @ResponseBody
    public IResult intoDept(String id) {

        if (service.changeState(Constants.SCWC, id)) {
            return new ResultBean<Boolean>(true);
        }
        return new ResultBean<String>("4011",">>>>>>>操作失败<<<<<<<");
    }

    @RequestMapping(value = "/selectByProductId", method = RequestMethod.POST)
    @ResponseBody
    public IResult selectByProductId(String productId) {

        return new ResultBean<LenBillCheck>(service.selectByProductId(productId));
    }
}