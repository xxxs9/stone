package com.gameloft9.demo.utils;

import com.gameloft9.demo.dataaccess.model.system.SysFinanceBill;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/04/07 2019-04-07
 *
 * 数据导出
 */
public class ExportUtil {
    public static void exprotData(HttpServletRequest request, HttpServletResponse response, List<SysFinanceBill> billList,String fileName){
        //使用poi将数据写到excel中
        //在内存中创建一个Excel文件
        HSSFWorkbook workbook =new HSSFWorkbook();
        //创建一个sheet标签页
        HSSFSheet sheet = workbook.createSheet("数据报表");
        sheet.autoSizeColumn(2);
        //创建标题行
        HSSFRow headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("部门");
        headRow.createCell(1).setCellValue("申请人");
        headRow.createCell(2).setCellValue("申请时间");
        headRow.createCell(3).setCellValue("货品名称");
        headRow.createCell(4).setCellValue("货款类型");
        headRow.createCell(5).setCellValue("款额");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:mm");
        for (SysFinanceBill bill : billList) {
            HSSFRow dateRow = sheet.createRow(sheet.getLastRowNum() + 1);
            dateRow.createCell(0).setCellValue(bill.getDepartment());
            dateRow.createCell(1).setCellValue(bill.getApplyUser());
            String time = format.format(bill.getBillTime());
            dateRow.createCell(2).setCellValue(time);
            dateRow.createCell(3).setCellValue(bill.getGoodsName());
            dateRow.createCell(4).setCellValue(bill.getBalanceType());
            dateRow.createCell(5).setCellValue(bill.getBalance());

        }

        //使用输出流进行文件下载（一个流，两个头）
        /*String fileName = "财务报表.xls";*/
        String contentType = request.getServletContext().getMimeType(fileName);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setContentType(contentType);

        //获取客户端浏览器类型
        String agent = request.getHeader("User-Agent");
        try {
            fileName = FileUtils.encodeDownloadFilename(fileName,agent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setHeader("content-disposition","attachment;filename="+fileName);
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
