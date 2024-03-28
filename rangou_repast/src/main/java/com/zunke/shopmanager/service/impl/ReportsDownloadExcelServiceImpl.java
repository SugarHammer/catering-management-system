package com.zunke.shopmanager.service.impl;

import com.zunke.shopmanager.mapper.ReportsOrdersMapper;
import com.zunke.shopmanager.pojo.Orders;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yiming
 * @version 1.0
 * @since 2021/9/15 10:22
 */
@Service
public class ReportsDownloadExcelServiceImpl {
    @Resource
    private ReportsOrdersMapper reportsOrdersMapper;

    public void excelOfAccount(String ids, HttpServletResponse response) throws IOException {
        String[] idArray = ids.split(",");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(idArray));
        //list.addAll(Arrays.asList(idArray));

        List<Orders> ordersList = reportsOrdersMapper.selectByIds(list);
        System.out.println(ordersList);

        Workbook workbook = new HSSFWorkbook();                        //  创建2003 excel对象 HSSFWorkbook类型对应2003版本    XSSFWorkbook类型对应2007之后版本
        Sheet sheet = workbook.createSheet("流水简表");             // 创建第一个sheet（页），并命名，注意这里只创建一页，如果业务需求，可以添加多页

        Font f01 = workbook.createFont();                                // 创建字体
        f01.setFontHeightInPoints((short) 10);                     // 创建字体样式：字体大小
        f01.setColor(IndexedColors.BLACK.getIndex());              // 创建字体样式：字体类型(这里设置的是黑体)
        f01.setBold(true);                                         // 创建字体样式：粗体

        Font f02 = workbook.createFont();                                // 创建字体
        f02.setFontHeightInPoints((short) 10);                     // 创建字体样式：字体大小
        f02.setColor(IndexedColors.BLACK.getIndex());              // 创建字体样式：字体类型(这里设置的是黑体)
        f02.setBold(false);

        CellStyle cs01 = workbook.createCellStyle();                     // 创建单元格每列格式对象
        cs01.setFont(f01);                                           // 把字体样式保存到样式单中
        cs01.setBorderLeft(BorderStyle.THIN);                      // 设置具有边框的效果：左边框
        cs01.setBorderRight(BorderStyle.THIN);                     // 设置具有边框的效果：右边框
        cs01.setBorderTop(BorderStyle.THIN);                       // 设置具有边框的效果：上边框
        cs01.setBorderBottom(BorderStyle.THIN);                    // 设置具有边框的效果：下边框
        cs01.setAlignment(HorizontalAlignment.CENTER);             // 设置文字居中的效果

        CellStyle cs02 = workbook.createCellStyle();                     // 创建单元格每列格式对象
        cs02.setFont(f02);                                           // 把字体样式保存到样式单中
        cs02.setBorderLeft(BorderStyle.THIN);                      // 设置具有边框的效果：左边框
        cs02.setBorderRight(BorderStyle.THIN);                     // 设置具有边框的效果：右边框
        cs02.setBorderTop(BorderStyle.THIN);                       // 设置具有边框的效果：上边框
        cs02.setBorderBottom(BorderStyle.THIN);                    // 设置具有边框的效果：下边框
        cs02.setAlignment(HorizontalAlignment.CENTER);             // 设置文字居中的效果

        Row row = sheet.createRow(0);                      // 在当前sheet页上创建一个新行
        createCell(0, "订单id", cs01, sheet, row);
        createCell(1, "订单时间", cs01, sheet, row);
        createCell(2, "订单消费", cs01, sheet, row);

        for (int i = 0; i < ordersList.size(); i++) {                 // 设置每行每列的值  Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            row = sheet.createRow(i + 1);                      // 在当前sheet页上创建一个新行
            Orders orders = ordersList.get(i);
            createCell(0, orders.getId().toString(), cs02, sheet, row);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String payDate = sdf.format(orders.getPayDate());
            createCell(1, payDate, cs02, sheet, row);
            createCell(2, orders.getTotalPrice().toString(), cs02, sheet, row);
        }

        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition","attachment; filename=" + System.currentTimeMillis() + ".xls");
        //获取响应报文输出流对象
        ServletOutputStream out = response.getOutputStream();
        //输出
        workbook.write(out);
        out.flush();
        out.close();
    }

    public void createCell(int cellNum, String cellValue, CellStyle cs, Sheet sheet, Row row01) {
        sheet.setColumnWidth(cellNum, 256 * 35);            // 设置列宽。第一个参数表示要为第几列，第二个参数表示列的宽度，值为像素值。
        Cell cell01 = row01.createCell(cellNum);               // 在row行上创建一列
        cell01.setCellValue(cellValue);                        // 在此列上写入数据
        cell01.setCellStyle(cs);                               // 在此列上添加样式
    }
}
