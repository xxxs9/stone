package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.LenProduct;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.LenProductService;
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

    @RequestMapping("/del")
    @ResponseBody
    public IResult delete(String id){
        if (service.delete(id)){
            Boolean flag= true;
            return new ResultBean(flag);
        }else{
            ResultBean<?> result = new ResultBean();
            result.setMsg("权限不足！");
            result.setCode(ResultBean.SYSTEM_FAIL);
            return result;
        }

    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    public  IResult getById(String id){
        return  new ResultBean<LenProduct>(service.getByPrimaryKey(id));
    }

    /**
     * 获取产品id
     * */
    @RequestMapping(value = "/getId",method = RequestMethod.POST)
    @ResponseBody
    public  IResult getProductId(){
        return  new ResultBean<List<String>>(service.getProductId());
    }

    @RequestMapping(value = "/chg1",method = RequestMethod.POST)
    @ResponseBody
    public  IResult changeState1(String id){
        return  new ResultBean<Boolean>(service.changeState(id));
    }

    @RequestMapping(value = "/chg",method = RequestMethod.POST)
    @ResponseBody
    public  IResult changeState(String id){

        if (service.changeState(id)){
            Boolean flag= true;
            return new ResultBean(flag);
        }else{
//            return new ResultBean(new CheckException("权限不足","权限不足"));
            ResultBean<?> result = new ResultBean();
            result.setMsg("权限不足！");
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
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/sb",method = RequestMethod.POST)
    @ResponseBody
    public IResult stepBack(String id) {
        boolean flag=true;
        if (service.stepBack(id)) {
            return new ResultBean(flag);
        } else {
            ResultBean<?> result = new ResultBean();
            result.setMsg("操作不被允许");
            result.setCode(ResultBean.SYSTEM_FAIL);
            return result;
        }
    }

}
