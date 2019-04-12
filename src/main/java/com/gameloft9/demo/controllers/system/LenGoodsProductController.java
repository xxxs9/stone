package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.LenGoodsProduct;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.LenGoodsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-04-05 - 09:08
 * @description :
 */
@Controller
@RequestMapping("/goodsProduct")
public class LenGoodsProductController {
    @Autowired
    LenGoodsProductService service;


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAll() {
        return new ResultBean<List>(service.selectAll());
    }

    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAllByPage(String page, String limit, String pname) {
        return new PageResultBean<List>(service.selectByPage(page, limit, pname), service.dataCount());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public IResult add(LenGoodsProduct lenGoodsProduct) {
        return new ResultBean<Boolean>(service.insert(lenGoodsProduct));
    }

    @RequestMapping(value = "/upd", method = RequestMethod.POST)
    @ResponseBody
    public IResult update(LenGoodsProduct lenGoodsProduct ){
        return new ResultBean<Boolean>(service.update(lenGoodsProduct));
    }

    @RequestMapping("/del")
    @ResponseBody
    public IResult delete(String id) {
        if (service.delete(id)) {
            Boolean flag = true;
            return new ResultBean(flag);
        } else {
            return new ResultBean<String>("4011",">>>>>>>删除失败<<<<<<<");
        }

    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public IResult getById(String id) {
        return new ResultBean<LenGoodsProduct>(service.getByPrimaryKey(id));
    }

    @RequestMapping(value = "/getUnProduce", method = RequestMethod.POST)
    @ResponseBody
    public IResult getUnProduce() {
        return new ResultBean<List>(service.selectByUnProduce());
    }

    @RequestMapping(value = "/getbyBH", method = RequestMethod.POST)
    @ResponseBody
    public IResult getUnProduce(String bianhao) {
        return new ResultBean<LenGoodsProduct>(service.getByBH(bianhao));
    }



}
