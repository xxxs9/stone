package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.LenProduct;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.LenProductService;
import com.gameloft9.demo.utils.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @packageName: com.gameloft9.demo.controllers.system
 * @author: Lennon_Yuan
 * @time: 2019/3/19 0019 - 下午 3:24
 * @description:
 */
@Controller
@RequestMapping("/product")
public class LenProductController {
    @Autowired
    LenProductService service;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAll(){
       return new ResultBean<List>( service.selectAll());
    }

    @RequestMapping(value = "/pageList",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAllByPage(String page, String limit, String productName, String state){
        return new PageResultBean<List>(service.selectByPage(page,limit,productName,state),service.dataCount(state));
    }

    @RequestMapping("/add")
    @ResponseBody
    public IResult add(LenProduct lenProduct){
        return new ResultBean<Boolean>(service.insert(lenProduct));
    }

    @RequestMapping("/upd")
    @ResponseBody
    public IResult update(LenProduct lenProduct){
        return  new ResultBean<Boolean>(service.update(lenProduct));
    }

    /**
     * 识别操作的人才能操作
     * @param id
     * @return
     */
    @RequestMapping("/del")
    @ResponseBody
    public IResult delete(String id){
        LenProduct product = service.getByPrimaryKey(id);
        String productOther2 = product.getCanSold();
        String sysUser = (String) SecurityUtils.getSubject().getSession().getAttribute("sysUser");
        if (productOther2.equals(sysUser)){
            if (service.delete(id)){
                return new ResultBean<Boolean>(true);

            }else{
                throw new RuntimeException(">>>>>>>操作失败<<<<<<<");
            }
        }else{
            throw new RuntimeException(">>>>>>>无权删除其他人的工单<<<<<<<");
        }

    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    public  IResult getById(String id){
        return  new ResultBean<LenProduct>(service.getByPrimaryKey(id));
    }

    @RequestMapping(value = "/chg1",method = RequestMethod.POST)
    @ResponseBody
    public  IResult changeState1(String id){
        return  new ResultBean<Boolean>(service.changeState(id));
    }

    /**
     * 判断操作的人才能进行操作
     * @param id
     * @return
     */
    @RequestMapping(value = "/chg",method = RequestMethod.POST)
    @ResponseBody
    public  IResult changeState(String id){
        Subject subject = SecurityUtils.getSubject();
        //获取当前用户
        String currentUserId = (String) subject.getSession().getAttribute("sysUser");
        LenProduct product = service.getByPrimaryKey(id);
        String productOther2 = product.getCanSold();
        //实体内创建的用户和当前用户进比较 是否一致
        if (productOther2.equals(currentUserId)){
            if (service.changeState(id)){
                return new ResultBean(true);
            }else{
                ResultBean<?> result = new ResultBean();
                result.setMsg(">>>>>>>数据库操作失败<<<<<<<");
                result.setCode(ResultBean.SYSTEM_FAIL);
                return result;
             }
              }else{
                  ResultBean<?> result = new ResultBean();
                  result.setMsg(">>>>>>>权限不足<<<<<<<");
                     result.setCode(ResultBean.SYSTEM_FAIL);
                     return result;
        }


    }

    /**
     * 通过状态查询（gbs = getByState）
     * @return
     */
    @RequestMapping(value = "/gbs",method = RequestMethod.POST)
    @ResponseBody
    public  IResult changeState(){
        return  new ResultBean<List>(service.selectByState());
    }


    /**
     * 状态回退
     * @param id
     * @return
     */
    @RequestMapping(value = "/sb",method = RequestMethod.POST)
    @ResponseBody
    public IResult stepBack(String id) {

        if (Constants.lennonPDAudi() == 1) {

            if (service.changeProState(Constants.productState.TIJIAO_UNAUDI, id)) {
                return new ResultBean<Boolean>(true);
            } else {
                ResultBean<?> result = new ResultBean();
                result.setMsg("操作不被允许");
                result.setCode(ResultBean.SYSTEM_FAIL);
                return result;
            }
        } else {
            ResultBean<?> result = new ResultBean();
            result.setMsg("操作不被允许");
            result.setCode(ResultBean.SYSTEM_FAIL);
            return result;
        }
    }


    /**
     * 产品入库的状态
     * @param id
     * @return
     */
    @RequestMapping(value = "/intoDepot",method = RequestMethod.POST)
    @ResponseBody
    public IResult intoDepot(String id) {
        if(service.changeProState(Constants.INTO_DEPOT,id)){
            return new ResultBean<Boolean>(true);
        }
        throw new RuntimeException("操作失败");
    }

    /**
     * 主管审核加工单
     * @param id
     * @return
     */
    @RequestMapping(value = "/managerAudi",method = RequestMethod.POST)
    @ResponseBody
    public IResult managerAudi(String id) {

        if (Constants.lennonPDAudi() == 1) {
            if (service.changeProState(Constants.productState.REACH_UNFENPEI, id)) {
                return new ResultBean<Boolean>(true);
            } else {
                throw new RuntimeException("操作失败");
            }
        } else {
            throw new RuntimeException("操作失败");
        }
    }

    /**
     * 暂停生产
     * @param id
     * @return
     */
    @RequestMapping("/stopProduce")
    @ResponseBody
    public IResult stopProduce(String id){

            if (service.changeBehindState(Constants.productState.STOP_PRODUCE,id)){
                return new ResultBean<Boolean>(true);
            }else {
                ResultBean<?> result = new ResultBean();
                result.setMsg(">>>>>>>数据库操作失败<<<<<<<");
                result.setCode(ResultBean.SYSTEM_FAIL);
                return result;
            }
        }


    /**
     * 开始生产
     * @param id
     * @return
     */
    @RequestMapping("/startProduce")
    @ResponseBody
    public IResult startProduce(String id){
            if (service.changeBehindState(Constants.productState.FENPEI_START_PRODUCE,id)){
                return new ResultBean<Boolean>(true);
            }else {
                ResultBean<?> result = new ResultBean();
                result.setMsg(">>>>>>>数据库操作失败<<<<<<<");
                result.setCode(ResultBean.SYSTEM_FAIL);
                return result;
            }


    }
    @RequestMapping("/reach.do")
    @ResponseBody
    public IResult reachDo(String id){
        if (service.changeBehindState(Constants.productState.AUDI_UNREACH,id)){
            return new ResultBean<Boolean>(true);
        }else {
            ResultBean<?> result = new ResultBean();
            result.setMsg(">>>>>>>数据库操作失败<<<<<<<");
            result.setCode(ResultBean.SYSTEM_FAIL);
            return result;
        }


    }
    /**
     * 生产完成
     * @param id
     * @return
     */
    @RequestMapping("/completeProduce")
    @ResponseBody
    public IResult completeProduce(String id){
        if(SecurityUtils.getSubject().hasRole(Constants.PRODUCE_ADMIN)){
            if (service.changeBehindState(Constants.productState.COMPLETE_PRODUCE,id)){
                return new ResultBean<Boolean>(true);
            }else {
                ResultBean<?> result = new ResultBean();
                result.setMsg(">>>>>>>数据库操作失败<<<<<<<");
                result.setCode(ResultBean.SYSTEM_FAIL);
                return result;
            }
        }
        ResultBean<?> result = new ResultBean();
        result.setMsg(">>>>>>>操作无法完成<<<<<<<");
        result.setCode(ResultBean.SYSTEM_FAIL);
        return result;
    }

    /**
     * 继续生产
     */
    @RequestMapping("/continueProduce")
    @ResponseBody
    public IResult continueProduce(String id){
        if(SecurityUtils.getSubject().hasRole(Constants.PRODUCE_ADMIN)){
            if (service.changeBehindState(Constants.productState.CONTINUE_PRODUCE,id)){
                return new ResultBean<Boolean>(true);
            }else {
                ResultBean<?> result = new ResultBean();
                result.setMsg(">>>>>>>数据库操作失败<<<<<<<");
                result.setCode(ResultBean.SYSTEM_FAIL);
                return result;
            }
        }
        ResultBean<?> result = new ResultBean();
        result.setMsg(">>>>>>>操作无法完成<<<<<<<");
        result.setCode(ResultBean.SYSTEM_FAIL);
        return result;
    }

    /**
     * 检验合格
     * @param id
     * @return
     */
    @RequestMapping("/checkOk")
    @ResponseBody
    public IResult checkOk(String id){
        if(SecurityUtils.getSubject().hasRole(Constants.PRODUCE_ADMIN)){
            if (service.changeBehindState(Constants.productState.CHECK_GOOD,id)){
                return new ResultBean<Boolean>(true);
            }else {
                ResultBean<?> result = new ResultBean();
                result.setMsg(">>>>>>>数据库操作失败<<<<<<<");
                result.setCode(ResultBean.SYSTEM_FAIL);
                return result;
            }
        }
        ResultBean<?> result = new ResultBean();
        result.setMsg(">>>>>>>操作无法完成<<<<<<<");
        result.setCode(ResultBean.SYSTEM_FAIL);
        return result;
    }

    /**
     * 检验不合格
     * @param id
     * @return
     */
    @RequestMapping("/checkBad")
    @ResponseBody
    public IResult checkBad(String id){
        if(SecurityUtils.getSubject().hasRole(Constants.PRODUCE_ADMIN)){
            if (service.changeBehindState(Constants.productState.CHECK_BAD,id)){
                return new ResultBean<Boolean>(true);
            }else {
                ResultBean<?> result = new ResultBean();
                result.setMsg(">>>>>>>数据库操作失败<<<<<<<");
                result.setCode(ResultBean.SYSTEM_FAIL);
                return result;
            }
        }
        ResultBean<?> result = new ResultBean();
        result.setMsg(">>>>>>>操作无法完成<<<<<<<");
        result.setCode(ResultBean.SYSTEM_FAIL);
        return result;
    }

    /**
     * 未入库
     * @param id
     * @return
     */
    @RequestMapping("/unIntoDepot")
    @ResponseBody
    public IResult unIntoDepot(String id){
        if(SecurityUtils.getSubject().hasRole(Constants.PRODUCE_ADMIN)){
            if (service.changeBehindState(Constants.productState.UN_INTO_DEPOT,id)){
                return new ResultBean<Boolean>(true);
            }else {
                ResultBean<?> result = new ResultBean();
                result.setMsg(">>>>>>>数据库操作失败<<<<<<<");
                result.setCode(ResultBean.SYSTEM_FAIL);
                return result;
            }
        }
        ResultBean<?> result = new ResultBean();
        result.setMsg(">>>>>>>操作无法完成<<<<<<<");
        result.setCode(ResultBean.SYSTEM_FAIL);
        return result;
    }

    @RequestMapping("/IntoDepot")
    @ResponseBody
    public IResult IntoDepot(String id){
        if(SecurityUtils.getSubject().hasRole(Constants.PRODUCE_ADMIN)){
            if (service.changeBehindState(Constants.productState.INTO_DEPOT,id)){
                return new ResultBean<Boolean>(true);
            }else {
                ResultBean<?> result = new ResultBean();
                result.setMsg(">>>>>>>数据库操作失败<<<<<<<");
                result.setCode(ResultBean.SYSTEM_FAIL);
                return result;
            }
        }
        ResultBean<?> result = new ResultBean();
        result.setMsg(">>>>>>>操作无法完成<<<<<<<");
        result.setCode(ResultBean.SYSTEM_FAIL);
        return result;
    }


}
