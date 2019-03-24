package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.DepotInventory;
import com.gameloft9.demo.dataaccess.model.system.DepotPersonnel;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.service.api.system.DepotInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * @author: Sxiu
 * @create: 2019/3/22 14:01
 * @description:
 */

@Slf4j
@Service
@RequestMapping("/depotInventory")
public class DepotInventoryController {

    @Autowired
    DepotInventoryService depotInventoryServiceImpl;
    /**
     * 获取库存列表
     * @param page                  页序
     * @param limit                 分页大小
     * @param type                  货品(原料/成品）
     */
    @RequestMapping(value = "/depotInventoryList.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getDepotPersonnelList(String page, String limit, String type,String goodsId){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new PageResultBean<Collection<DepotInventory>>(depotInventoryServiceImpl.getAll(page,limit,type,goodsId),depotInventoryServiceImpl.countGetAll(type,goodsId));
    }
}
