package com.zunke.shopmanager.service.impl;
/**
 * @author ：heye
 * @date 2021-09-20 22:12
 * @version ：1.0
 */

import com.zunke.shopmanager.mapper.MumberMapper;
import com.zunke.shopmanager.pojo.Mumber;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UploadNumberInforServiceImpl
 * @Date 2021/9/20 22:12
 * @Author 何永波
 * @Description TODO
 */
@Service("uploadNumberInforServiceImpl")
public class UploadNumberInforServiceImpl {

    @Resource
    private MumberMapper mumberMapper;

    @Transactional
    public Map<String,Object> uploadMumberInfor(MultipartFile file, HttpServletRequest request, String abPath) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String uploadPath = request.getServletContext().getRealPath("")+ File.separator+abPath+File.separator;//获得当前项目的硬盘物理地址
        String fileName = file.getOriginalFilename(); //获得上传的文件名

        File newFile = new File(uploadPath+fileName);
        FileCopyUtils.copy(file.getBytes(), newFile);
        String path = newFile.toString();
        String fileType = path.substring(path.lastIndexOf(".")+1);//获取文件后缀名
        InputStream is = new FileInputStream(path);//生成输入流
        HSSFWorkbook workbook = new HSSFWorkbook(is);//HSSFWorkbook对应2007以前的excel版本
        Sheet sheet = workbook.getSheetAt(0);
        Mumber mumber = null;
        for (int i = 0;i<sheet.getLastRowNum();i++){
            Row row = sheet.getRow(i + 1);
            mumber = new Mumber();
            Cell cell = row.getCell(0);
            cell.setCellType(CellType.STRING);
            mumber.setMumberName(cell.getStringCellValue());
            cell = row.getCell(1);
            cell.setCellType(CellType.STRING);
            mumber.setMumberPhone(cell.getStringCellValue());
            cell = row.getCell(2);
            cell.setCellType(CellType.STRING);
            mumber.setMumberEmail(cell.getStringCellValue());
            cell = row.getCell(3);
            cell.setCellType(CellType.STRING);
            mumber.setMumberBalance(Double.parseDouble(cell.getStringCellValue()));
            cell = row.getCell(4);
            cell.setCellType(CellType.STRING);
            mumber.setMumberIntegral(Integer.parseInt(cell.getStringCellValue()));
            mumber.setMumberRergistrationTime(new Date());
            mumber.setRoleId(0);
            mumberMapper.insert(mumber);
        }


        map.put("code", 200);
        map.put("msg", "批量新增成功!");

        return map;
    }
}
