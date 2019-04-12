package com.gameloft9.demo.controllers.system;
import com.gameloft9.demo.dataaccess.model.system.MarkerOrderTest;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.MarkerOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/marker")
public class MarkerOrderController {

   @Autowired
   MarkerOrderService markerOrderService;


    /**
     * 处理时间
     * @param binder
     * @param request
     */
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request){
        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // CustomDateEditor为自定义日期编辑器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 获取所有销售订单信息
     * @param page
     * @param limit
     * @param productId
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public IResult findAll(String page, String limit, String productId,String applyUser){
        List<MarkerOrderTest> list = markerOrderService.findAll(page, limit, productId,applyUser);
        return new PageResultBean<Collection<MarkerOrderTest>>(list,markerOrderService.countGetAll(productId,applyUser));
    }

    /**
     * 删除
     * @param id
     * @return
     */
   @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public IResult deleteById(String id){
       return new ResultBean(markerOrderService.deleteById(id));
   }

    /**
     * 修改
     * @param markerOrderTest
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public IResult update(MarkerOrderTest markerOrderTest){
        return new ResultBean<Boolean>(markerOrderService.update(markerOrderTest));
    }

    /**
     * 获取销售订单ID
     * @param id
     * @return
     */
    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    public IResult getMaker(String id){
        return new ResultBean<MarkerOrderTest>(markerOrderService.getMaker(id));
    }

    /**
     * 增加
     * @param markerOrderTest
     * @param request
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sale:add")
    public IResult add(MarkerOrderTest markerOrderTest, HttpServletRequest request){
        request.getSession().getAttribute("sysUser");
        System.out.println(markerOrderTest);
        return new ResultBean<String>(markerOrderService.add(markerOrderTest));
    }
    /**
     * 获取所有类表信息
     */
   /* @RequestMapping(value = "/selectAll",method = RequestMethod.POST)
      @ResponseBody
    public IResult selectAll(){
        List<MarkerOrderTest> list = markerOrderService.selectAll();
        return new ResultBean<Collection<MarkerOrderTest>>(list);
    }*/

    /**
     * 提交
     * @param markerOrderTest
     * @return
     */
    @RequestMapping(value = "/audi",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sale:add")
    public IResult audiUpdate(MarkerOrderTest markerOrderTest){
        return new ResultBean<Boolean>(markerOrderService.audiUpdate(markerOrderTest));
    }

    /**
     * 撤回
     * @param markerOrderTest
     * @return
     */
    @RequestMapping(value = "/back",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sale:add")
    public IResult backUpdate(MarkerOrderTest markerOrderTest){
        return new ResultBean<Boolean>(markerOrderService.backUpdate(markerOrderTest));
    }


    /**
     * 获取下拉框值
     * @return
     */
    @RequestMapping(value = "/getprocutid",method = RequestMethod.POST)
    @ResponseBody
    public IResult getProductId(){
        return new ResultBean<Collection<MarkerOrderTest>>(markerOrderService.getProductId());
    }

    /**
     * 提交仓库审核
     * @param markerOrderTest
     * @return
     */
    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    @ResponseBody
    public IResult submit(MarkerOrderTest markerOrderTest){
        return new ResultBean<Boolean>(markerOrderService.submit(markerOrderTest));
    }

    /**
     * 提交财务
     * @param markerOrderTest
     * @return
     */
    @RequestMapping(value = "/fina",method = RequestMethod.POST)
    @ResponseBody
    public IResult fina(MarkerOrderTest markerOrderTest){
        return new ResultBean<Boolean>(markerOrderService.fina(markerOrderTest));
    }
}
