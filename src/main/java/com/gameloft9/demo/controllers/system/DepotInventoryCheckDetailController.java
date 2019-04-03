package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.DepotInventoryCheckDetail;
import com.gameloft9.demo.dataaccess.model.system.SysSupplier;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.DepotInventoryCheckDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author: Sxiu子
 * @create: 2019/3/31 14:49
 * @description:
 */
@Slf4j
@Controller
@RequestMapping("/depotInventoryCheckDetail")
public class DepotInventoryCheckDetailController {
    @Autowired
    private DepotInventoryCheckDetailService depotInventoryCheckDetailServiceImpl;

    /**
     * 获取盘点单明细记录数据
     * @param page                  页序
     * @param limit                 分页大小
     * @param checkId               盘点单ID
     * @param type                  货品（原料/成品）
     * @param goodsId               原料/成品ID
     */
    @RequestMapping(value = "/depotInventoryCheckDetailList.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getDepotInventoryCheckDetailList(String page, String limit,String checkId,String type,String goodsId){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new PageResultBean<Collection<DepotInventoryCheckDetail>>(depotInventoryCheckDetailServiceImpl.getAll(page,limit,checkId,type,goodsId),depotInventoryCheckDetailServiceImpl.countGetAll(checkId,type,goodsId));
    }

    /**
     * 新增盘点单明细
     * @param checkId       盘点单ID
     * @param type          货品（原料/成品）
     * @param goodsId       原料/成品ID
     * @param goodsNumber   货品数量
     * */
    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "新增盘点单明细")
    public IResult addDepotInventoryCheckDetail(String checkId, String type, String goodsId,String goodsNumber){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<String>(depotInventoryCheckDetailServiceImpl.addDepotInventoryCheckDetail(checkId,type,goodsId,goodsNumber));
    }

    /**
     * 批量添加盘点单明细
     * @param checkId           盘点单ID
     * @param types             多个货品（原料/成品）
     * @param goodsIds          多个原料/成品ID
     * @param goodsNumbers      多个货品数量
     * */
    @RequestMapping(value = "/adds.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "批量添加盘点单明细")
    public IResult addsDepotInventoryCheckDetail(String checkId,String types,String goodsIds,String goodsNumbers){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(depotInventoryCheckDetailServiceImpl.adds(checkId,types,goodsIds,goodsNumbers));
    }


    /**
     * 根据主键获取盘点单明细信息
     * @param id 盘点单明细主键
     * */
    @RequestMapping(value = "/get.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getDepotInventoryCheck(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<DepotInventoryCheckDetail>(depotInventoryCheckDetailServiceImpl.getById(id));
    }


    /**
     * 更新盘点单明细
     * @param id            盘点单明细ID
     * @param checkNumber   盘点数量
     * */
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "更新盘点单明细")
    public IResult updateSupplier(String id,String checkNumber, HttpServletRequest request){//传递了数组，前台放在payload里面了，后台通过@RequestBody获取
        //返回json至前端的均返回ResultBean或者PageResultBean
        String checkUser = (String) request.getSession().getAttribute("sysUser");
        return new ResultBean<Boolean>(depotInventoryCheckDetailServiceImpl.updateDepotInventoryCheckDetail(id,checkUser,checkNumber));
    }

    /**
     * 删除仓库
     * */
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "删除盘点单明细")
    public IResult deleteDepotInventoryCheck(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(depotInventoryCheckDetailServiceImpl.deleteDepotInventoryCheckDetail(id));
    }


    /**
     * 批量删除仓库
     * */
    @RequestMapping(value = "/dels.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "批量删除盘点单明细")
    public IResult delsDepotInventoryCheck(String ids){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(depotInventoryCheckDetailServiceImpl.delsDepotInventoryCheckDetail(ids));
    }
}
