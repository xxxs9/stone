package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.PurchaseReturn;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.PurchaseReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * PurchaseOrder controller控制层
 * @author OliverCH
 * @date 2019/03/24
 */
@Controller
@RequestMapping("/purchase_return")
public class PurchaseReturnController {

    @Autowired
    PurchaseReturnService service;

    /**
     * 获取实时时间*/
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    /**
     *查询所有列表*/
    @RequestMapping(value = "/list.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAll(String page,String limit,String goodsId,String depotState){
        return new PageResultBean<Collection<PurchaseReturn>>(service.selectAll(page,limit,goodsId,
                depotState),service.countGetAll(goodsId,depotState));
    }

    /**
     * 增加*/
    @RequestMapping(value = "/insert.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult insert(PurchaseReturn purchaseReturn){
        return new ResultBean<String>(service.insert(purchaseReturn));
    }

    /**
     * 删除*/
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult deleteByPrimaryKey(String id){
        return new ResultBean<Boolean>(service.deleteByPrimaryKey(id));
    }

    /**
     * 根据id获取信息*/
    @RequestMapping(value = "/get.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectByPrimaryKey(String id){
        return new ResultBean<PurchaseReturn>(service.selectByPrimaryKey(id));
    }

    /**
     * 修改*/
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult update(PurchaseReturn purchaseReturn){
        return new ResultBean<Boolean>(service.update(purchaseReturn));
    }

    /**
     * 获取goodsId下拉框*/
    @RequestMapping("/goodsId.do")
    @ResponseBody
    public IResult selectAllGoodsId(){
        return new ResultBean<Collection<PurchaseReturn>>(service.selectAllGoodsId());
    }

    /**
     * 获取orderNumber下拉框*/
    @RequestMapping("/orderNumber.do")
    @ResponseBody
    public IResult selectAllOrderNumber(){
        return new ResultBean<Collection<PurchaseReturn>>(service.selectAllOrderNumber());
    }

    /**
     * 采购退货 提交*/
    @RequestMapping(value = "/commit.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult commitReUpdate(PurchaseReturn purchaseReturn){
        return new ResultBean<Boolean>(service.commitReUpdate(purchaseReturn));
    }

    /**
     * 采购退货 撤回*/
    @RequestMapping(value = "/back.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult backReUpdate(PurchaseReturn purchaseReturn){
        return new ResultBean<Boolean>(service.backReUpdate(purchaseReturn));
    }
}
