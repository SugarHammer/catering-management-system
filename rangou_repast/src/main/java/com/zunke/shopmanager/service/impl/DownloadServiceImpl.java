package com.zunke.shopmanager.service.impl;

import com.zunke.shopmanager.dto.DishesDTO;
import com.zunke.shopmanager.mapper.DishesMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DownloadServiceImpl {
    @Resource
    private DishesMapper dishesMapper;

    public void downloadExcel(String ids, HttpServletResponse response) throws IOException {
        String[] idArray = ids.split(",");//1,2,3,25,
        List<String> list = new ArrayList<>(Arrays.asList(idArray));
        System.out.println(list);
        List<DishesDTO> dishesList = dishesMapper.selectByIds(list);

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("菜品信息表");                      // 创建第一个sheet（页），并命名，注意这里只创建一页，如果业务需求，可以添加多页
        Font f = workbook.createFont();                                // 创建字体
        f.setFontHeightInPoints((short) 10);                     // 创建字体样式：字体大小
        f.setColor(IndexedColors.BLACK.getIndex());              // 创建字体样式：字体类型(这里设置的是黑体)
        f.setBold(true);                                         // 创建字体样式：粗体

        CellStyle cs = workbook.createCellStyle();                     // 创建单元格每列格式对象
        cs.setFont(f);                                           // 把字体样式保存到样式单中
        cs.setBorderLeft(BorderStyle.THIN);                      // 设置具有边框的效果：左边框
        cs.setBorderRight(BorderStyle.THIN);                     // 设置具有边框的效果：右边框
        cs.setBorderTop(BorderStyle.THIN);                       // 设置具有边框的效果：上边框
        cs.setBorderBottom(BorderStyle.THIN);                    // 设置具有边框的效果：下边框
        cs.setAlignment(HorizontalAlignment.CENTER);             // 设置文字居中的效果

        Row row = sheet.createRow(0);                      // 在当前sheet页上创建一个新行
        //在索引0的位置创建单元格（左上端）
        createCell(0, "菜品编号", cs, sheet, row);
        createCell(1, "菜品名称", cs, sheet, row);
        createCell(2, "菜品价格", cs, sheet, row);
        createCell(3, "所属大类", cs, sheet, row);
        createCell(4, "所属小类", cs, sheet, row);
        DishesDTO dishes = null;
        for (int i = 0; i < dishesList.size(); i++) {                 // 设置每行每列的值  Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            row = sheet.createRow(i + 1);                      // 在当前sheet页上创建一个新行
            dishes = dishesList.get(i);
            System.out.println(i);
            createCell(0, dishes.getDishesNo(), cs, sheet, row);
            createCell(1, dishes.getDishesName(), cs, sheet, row);
            createCell(2, dishes.getDishesPrice().toString(), cs, sheet, row);
            createCell(3, dishes.getCategoriesName(), cs, sheet, row);
            createCell(4, dishes.getSubcategoriesName(), cs, sheet, row);
        }
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".xls");
        //获取响应报文输出流对象
        ServletOutputStream out = response.getOutputStream();
        //输出
        workbook.write(out);
        out.flush();
        out.close();
    }
    public void createCell(int index, String value, CellStyle cs, Sheet sheet, Row row) {
        sheet.setColumnWidth(index, 256 * 35);
        Cell cell = row.createCell(index);
        cell.setCellValue(value);
        cell.setCellStyle(cs);
    }

//下载方法
    public boolean downloadDishesTemplate(String filePath,String fileName, String encoding, HttpServletResponse response, HttpServletRequest request){
        File f = new File(filePath);
        if (!f.exists()) {
            try {
                response.sendError(404, "File not found!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }


        String type = fileName.substring(fileName.lastIndexOf(".") + 1);
        //判断下载类型 xlsx 或 xls 现在只实现了xlsx、xls两个类型的文件下载
        if (type.equalsIgnoreCase("xlsx") || type.equalsIgnoreCase("xls")){
            response.setContentType("application/force-download;charset=UTF-8");
            final String userAgent = request.getHeader("USER-AGENT");
            try {
                if (StringUtils.contains(userAgent, "MSIE") || StringUtils.contains(userAgent, "Edge")) {// IE浏览器
                    fileName = URLEncoder.encode(fileName, "UTF8");
                } else if (StringUtils.contains(userAgent, "Mozilla")) {// google,火狐浏览器
                    fileName = new String(fileName.getBytes(), "ISO8859-1");
                } else {
                    fileName = URLEncoder.encode(fileName, "UTF8");// 其他浏览器
                }
                response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            } catch (UnsupportedEncodingException e) {
//                logger.error(e.getMessage(), e);
                return false;
            }
            InputStream in = null;
            OutputStream out = null;
            try {

                //获取要下载的文件输入流
                in = new FileInputStream(filePath);
                int len = 0;
                //创建数据缓冲区
                byte[] buffer = new byte[1024];
                //通过response对象获取outputStream流
                out = response.getOutputStream();
                //将FileInputStream流写入到buffer缓冲区
                while((len = in.read(buffer)) > 0) {
                    //使用OutputStream将缓冲区的数据输出到浏览器
                    out.write(buffer,0,len);
                }
                //这一步走完，将文件传入OutputStream中后，页面就会弹出下载框

            } catch (Exception e) {
//                logger.error(e.getMessage(), e);
                return false;
            } finally {
                try {
                    if (out != null)
                        out.close();
                    if(in!=null)
                        in.close();
                } catch (IOException e) {
//                    logger.error(e.getMessage(), e);
                }
            }
            return true;
        }else {
//            logger.error("不支持的下载类型！");
            return false;
        }
    }



}
