package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.SysMaterialGoodsService;
import com.gameloft9.demo.service.beans.system.MaterialGoodsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author shizhengyu
 * @Date 2019/3/27 - 09：55
 * @Description:
 **/
@Controller
@Slf4j
@RequestMapping("/materialGoods")
public class SysMaterialGoodsController {

    @Autowired
    SysMaterialGoodsService sysMaterialGoodsServiceImpl;

    /**
     * 分页获取供应关系列表
     * @param page                  页序
     * @param limit                 分页大小
     * @param supplierName          供应商名称
     * @param goodsName             货物名称
     * @param goodsType             货物类型
     * @param goodsSpecification    货物规格
     */
    @RequestMapping(value = "/materialGoodsList.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getMaterialGoodsList(String page, String limit, String supplierName, String goodsName, String goodsType, String goodsSpecification){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new PageResultBean<Collection<MaterialGoodsResponse>>(sysMaterialGoodsServiceImpl.getAll(page, limit,supplierName,goodsName,goodsType,goodsSpecification),sysMaterialGoodsServiceImpl.countGetAll(supplierName,goodsName,goodsType,goodsSpecification));
    }

    /**
     * 添加供应关系种类
     * @param supplierName          供应商名称
     * @param goodsName             货物名称
     * @param goodsPrice            货物价格
     * @param goodsOriginPlace      货物原产地
     * */
    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "添加供应关系")
    public IResult addMaterialGoods(String supplierName,String goodsName,String goodsPrice,String goodsOriginPlace,HttpServletRequest request){
        //返回json至前端的均返回ResultBean或者PageResultBean
        String imageUrl = (String) request.getSession().getAttribute("imageUrl");
        return new ResultBean<String>(sysMaterialGoodsServiceImpl.addMaterialGoods(supplierName,goodsName,goodsPrice,goodsOriginPlace,imageUrl));
    }

    /**
     * 根据主键获取供应关系信息
     * @param id 供应关系主键
     * */
    @RequestMapping(value = "/get.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getMaterialGoods(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<MaterialGoodsResponse>(sysMaterialGoodsServiceImpl.getById(id));
    }

    /**
     * 更新原料
     * @param supplierName          供应商名称
     * @param goodsName             货物名称
     * @param goodsPrice            货物价格
     * @param goodsOriginPlace      货物原产地
     */
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "更新供应关系")
    public IResult updateMaterialGoods(String id,String supplierName,String goodsName,String goodsPrice,String goodsOriginPlace,HttpServletRequest request){
        //返回json至前端的均返回ResultBean或者PageResultBean
        String imageUrl = (String) request.getSession().getAttribute("imageUrl");
        return new ResultBean<Boolean>(sysMaterialGoodsServiceImpl.updateMaterialGoods(id,supplierName,goodsName,goodsPrice,goodsOriginPlace,imageUrl));
    }

    /**
     * 删除
     * @param id 供应关系id
     * */
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "删除供应关系")
    public IResult deleteMaterialGoods(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(sysMaterialGoodsServiceImpl.deleteMaterialGoods(id));
    }

    /**
     * 用户头像上传
     * 图片文件上传
     * @param file
     *
     */
    @RequestMapping(value = "/uploadHeadImage.do", method = { RequestMethod.POST })
    @ResponseBody
    public ResultBean uploadHeadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request, InputStream stream, String goodsId){
        Assert.notNull(file, "上传文件不能为空");
        String src="/../../../../image/";
        System.out.println(request.getSession().getServletContext().getRealPath("/" + src));
        String path="D:/IntelliJ IDEA 2018.2.4/image";
        //System.currentTimeMillis()根据系统时间产生随机数，保证上传的图片名字不一样
        String name=System.currentTimeMillis()+file.getOriginalFilename();
        Map<String,Object> map = new HashMap<String, Object>();
        try {
        File dir = new File(path, name);
        src=src+name;
        request.getSession().setAttribute("imageUrl",src);
        if (!dir.exists()) {
            dir.mkdirs();
            map.put("code","1");
            map.put("msg","上传成功");
        }else{
            map.put("code","0");
            map.put("msg","上传失败");
        }
            file.transferTo(dir);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("code","0");
            map.put("msg","系统异常");
        }
        return new ResultBean<Map<String,Object>>(map);
    }

    /**
     * 根据获取原料商品id信息
     * */
    @RequestMapping(value = "/getId.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getMaterialGoodsId(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<List<String>>(sysMaterialGoodsServiceImpl.getMaterialGoodsId());
    }


}
