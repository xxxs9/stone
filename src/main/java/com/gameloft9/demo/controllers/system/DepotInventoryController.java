package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.DepotInventory;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.DepotInventoryService;
import com.gameloft9.demo.service.beans.system.MaterialInventory;
import com.gameloft9.demo.utils.Constants;
import com.gameloft9.demo.utils.FileUtils;
import com.gameloft9.demo.utils.Response;
import com.gameloft9.demo.utils.ResponseFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.PageData;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author: Sxiu
 * @create: 2019/3/22 14:01
 * @description:
 */

@Slf4j
@Controller
@RequestMapping("/depotInventory")
public class DepotInventoryController {

    @Autowired
    DepotInventoryService depotInventoryServiceImpl;
    /**
     * 获取库存列表
     * @param page                  页序
     * @param limit                 分页大小
     * @param type                  货品(原料/成品）
     * @param goodsId               原料/成品ID
     */
    @RequestMapping(value = "/depotInventoryList.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getDepotInventorylList(String page, String limit, String type,String goodsId){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new PageResultBean<Collection<DepotInventory>>(depotInventoryServiceImpl.getAll(page,limit,type,goodsId),depotInventoryServiceImpl.countGetAll(type,goodsId));
    }

    /**
     * 获取库存列表
     * @param page                  页序
     * @param limit                 分页大小
     * @param goodsId               原料/成品ID
     */
    @RequestMapping(value = "/materialInventorylList.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getMaterialInventorylList(String page, String limit,String goodsId){
        //返回json至前端的均返回ResultBean或者PageResultBean
        String type = Constants.MATERIAL;
        return new PageResultBean<Collection<MaterialInventory>>(depotInventoryServiceImpl.getMaterialInventory(page,limit,type,goodsId),depotInventoryServiceImpl.countGetAll(type,goodsId));
    }

    /**
     * 根据货物id获取库存信息
     * @param goodsId 货物id
     * */
    @RequestMapping(value = "/getByGoodsId.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getDepotInventoryByGoodsId(String goodsId){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<DepotInventory>(depotInventoryServiceImpl.findOne(goodsId));
    }

    /**
     * 根据货物id更新库存信息
     * @param id                    盘点单明细id
     * @param goodsId               货物id
     * @param goodsNumber           货物数量
     * */
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult updateGoodsNumber(String id,String goodsId,String goodsNumber){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(depotInventoryServiceImpl.updateGoodsNumber(id,goodsId,goodsNumber));
    }

    /**
     * 导出库存信息
     * @param request          请求
     * @param response         响应
     * @param goodsId          货物编号
     * @param type             货物类型
     */

    @RequestMapping(value = "export.do", method = RequestMethod.GET)
    public void exportExcelData(HttpServletRequest request, HttpServletResponse response,String goodsId,String type) throws  IOException {

        String fileName = "库存信息.xlsx";


        List<DepotInventory> InventoryList = depotInventoryServiceImpl.getAll("1","9999999",type,goodsId);

        File file = depotInventoryServiceImpl.exportInventory(InventoryList);

        if (file != null) {

            //获取客户端浏览器类型
            String agent = request.getHeader("User-Agent");
            try {
                fileName = FileUtils.encodeDownloadFilename(fileName,agent);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 设置响应头
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            FileInputStream inputStream = new FileInputStream(file);
            OutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[8192];

            int len;

            while ((len = inputStream.read(buffer, 0, buffer.length)) > 0) {
                outputStream.write(buffer, 0, len);
                outputStream.flush();
            }

            inputStream.close();
            outputStream.close();

        }
    }

    /**
     * 下载库存表模版
     * @param request          请求
     * @param response         响应
     */

    @RequestMapping(value = "template.do", method = RequestMethod.GET)
    public void exportStorageRecord(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        String fileName = "库存信息模版.xlsx";


        List<DepotInventory> InventoryList = depotInventoryServiceImpl.getAll("1","9999999","无",null);

        File file = depotInventoryServiceImpl.exportInventory(InventoryList);

        if (file != null) {

            //获取客户端浏览器类型
            String agent = request.getHeader("User-Agent");
            try {
                fileName = FileUtils.encodeDownloadFilename(fileName,agent);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 设置响应头
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            FileInputStream inputStream = new FileInputStream(file);
            OutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[8192];

            int len;

            while ((len = inputStream.read(buffer, 0, buffer.length)) > 0) {
                outputStream.write(buffer, 0, len);
                outputStream.flush();
            }

            inputStream.close();
            outputStream.close();

        }
    }

    /**
     * 导入库存信息
     *
     * @param file 保存有库存信息的文件
     * @return 返回一个map，其中：key 为 result表示操作的结果，包括：success 与
     * error；key为total表示导入的总条数；key为available表示有效的条数
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    @ResponseBody
    public IResult importStorageRecord(@RequestParam("file") MultipartFile file) {
        // 初始化 Response
        Response responseContent = ResponseFactory.newInstance();
        String result = Response.RESPONSE_RESULT_ERROR;

        int total = 0;
        int available = 0;

        if (file != null) {
            Map<String, Object> importInfo = depotInventoryServiceImpl.importInventory(file);
            if (importInfo != null) {
                total = (int) importInfo.get("total");
                available = (int) importInfo.get("available");

                result = Response.RESPONSE_RESULT_SUCCESS;
            }
        }

        // 设置 Response
        responseContent.setResponseResult(result);
        responseContent.setResponseTotal(total);
        responseContent.setCustomerInfo("available", available);
        return new ResultBean<Map<String, Object>> (responseContent.generateResponse()) ;
    }


}
