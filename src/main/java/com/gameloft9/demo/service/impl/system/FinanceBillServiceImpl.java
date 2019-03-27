package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.FinanceBillMapper;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceBill;
import com.gameloft9.demo.service.api.system.FinanceBillService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/26 2019-03-26
 */
@Service
@Transactional(rollbackFor = Exception.class)

public class FinanceBillServiceImpl implements FinanceBillService {

    @Autowired
    FinanceBillMapper billMapper;

    /**
     *
     * @param page 当前页
     * @param limit 当前页条数
     * @return
     *      账单集合
     */
    public List<SysFinanceBill> getAll(String page, String limit) {
        PageRange pageRange = new PageRange(page,limit);
        return billMapper.getAll(pageRange.getStart(),pageRange.getEnd());
    }

    /**
     *
     * @return
     *      总条数
     */
    public int getCount() {
        return billMapper.getCount();
    }

    /**
     * 导出数据
     */
    public void export(HttpServletRequest request, HttpServletResponse response) {
        //查找所有的对账
        List<SysFinanceBill> billList = billMapper.getAllBill();
        //使用poi将数据写到excel中
        //在内存中创建一个Excel文件
        HSSFWorkbook workbook =new HSSFWorkbook();
        //创建一个sheet标签页
        HSSFSheet sheet = workbook.createSheet("数据报表");
        sheet.autoSizeColumn(2);
        //创建标题行
        HSSFRow headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("部门");
        headRow.createCell(1).setCellValue("款额");
        headRow.createCell(2).setCellValue("时间");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:mm");
        for (SysFinanceBill bill : billList) {
            HSSFRow dateRow = sheet.createRow(sheet.getLastRowNum() + 1);
            dateRow.createCell(0).setCellValue(bill.getDepartment());
            dateRow.createCell(1).setCellValue(bill.getBalance());
            String time = format.format(bill.getBillTime());
            dateRow.createCell(2).setCellValue(time);
        }

        //使用输出流进行文件下载（一个流，两个头）
        String fileName = "财务报表.xls";
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
