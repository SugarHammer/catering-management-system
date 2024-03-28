package com.zunke.shopmanager.service.impl;/**
 * @author ：heye
 * @date 2021-09-16 16:15
 * @version ：1.0
 */

import com.zunke.shopmanager.Util.DateUtil;
import com.zunke.shopmanager.mapper.DownLaodNumberInforMapper;
import com.zunke.shopmanager.pojo.Mumber;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @ClassName DownLaodNumberInforServiceImpl
 * @Date 2021/9/16 16:15
 * @Author 何永波
 * @Description TODO 用户信息下载
 */
@Service
public class DownLaodNumberInforServiceImpl {

    @Resource
    private DownLaodNumberInforMapper downLaodNumberInforMapper;


    /**
     * 批量下载会员信息
     * @param ids
     * @param response
     * @return
     * @throws IOException
     */
    public void downloadNumberInfor(String ids, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();

        String[] idArray = ids.split(",");
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(idArray));
        List<Mumber> mumbers = downLaodNumberInforMapper.selectByIds(list);
        System.out.println(mumbers);


        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("会员信息表");
        CellStyle cs = wb.createCellStyle();
        Row row01 = sheet.createRow(0);
        createCell(0, "会员id",cs,sheet,row01);
        createCell(1, "会员姓名",cs,sheet,row01);
        createCell(2, "会员电话",cs,sheet,row01);
        createCell(3, "会员余额",cs,sheet,row01);
        createCell(4, "会员积分",cs,sheet,row01);
        createCell(5, "注册时间",cs,sheet,row01);
        //createCell(6, "会员等级",cs,sheet,row01);
        for(int i = 1;i<=mumbers.size();i++){
            Row row = sheet.createRow(i);
            Mumber mumber = mumbers.get(i-1);
            createCell(0, String.valueOf(mumber.getId()),cs,sheet,row);
            createCell(1, mumber.getMumberName(),cs,sheet,row);
            createCell(2, mumber.getMumberPhone(),cs,sheet,row);
            createCell(3, String.valueOf(mumber.getMumberBalance()),cs,sheet,row);
            createCell(4, String.valueOf(mumber.getMumberIntegral()),cs,sheet,row);


            createCell(5, DateUtil.dateToStringUtil(mumber.getMumberRergistrationTime()),cs,sheet,row);
            //createCell(6, mumber.getMumberLevel().getNumberLevelName(),cs,sheet,row);
        }

        if(wb!=null){
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="+System.currentTimeMillis()+".xls");
            ServletOutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        }else{
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("系统服务出错!");
        }
        map.put("code", 200);
        map.put("msg", "下载成功");

    }

    public void createCell(int cellNum,String cellValue,CellStyle cs,Sheet sheet,Row row){
        sheet.setColumnWidth(cellNum,256*35);
        Cell cell  = row.createCell(cellNum);
        cell.setCellValue(cellValue);
        cell.setCellStyle(cs);
    }

}
