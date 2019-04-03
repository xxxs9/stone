package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.AllRole;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.AllRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
@RequestMapping("/allRole")
public class AllRoleController {

    @Autowired
    AllRoleService service;

    /**通知部门*/
    @RequestMapping(value = "/notify.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectNotify(String limit){
        return new ResultBean<Collection<AllRole>>(service.selectNotify(limit));
    }

    /**供销商部门*/
    @RequestMapping(value = "/supplier.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectSupplier(String limit){
        return new ResultBean<Collection<AllRole>>(service.selectSupplier(limit));
    }

    /**销售部门*/
    @RequestMapping(value = "/sale.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectSale(String limit){
        return new ResultBean<Collection<AllRole>>(service.selectSale(limit));
    }

    /**采购部门*/
    @RequestMapping(value = "/proOrder.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectProOrder(String limit){
        return new ResultBean<Collection<AllRole>>(service.selectProOrder(limit));
    }

    /**仓库部门*/
    /*@RequestMapping(value = "/depot.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectDepot(String limit){
        return new ResultBean<Collection<AllRole>>(service.selectDepot(limit));
    }*/

    /**生产部门*/
    /*@RequestMapping(value = "/product.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectProduct(String limit){
        return new ResultBean<Collection<AllRole>>(service.selectProduct(limit));
    }*/

    /**财务部门*/
    @RequestMapping(value = "/finance.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectFinance(String limit){
        return new ResultBean<Collection<AllRole>>(service.selectFinance(limit));
    }

}
