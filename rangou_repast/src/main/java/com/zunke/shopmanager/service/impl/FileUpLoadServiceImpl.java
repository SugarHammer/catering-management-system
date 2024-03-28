package com.zunke.shopmanager.service.impl;

import com.zunke.shopmanager.mapper.DiningMapper;
import com.zunke.shopmanager.pojo.Dining;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author thinknovo
 * @version 1.0
 */
@Service
public class FileUpLoadServiceImpl {

    @Resource
    private DiningMapper diningMapper;

    /**
     * 上传文件处理
     * @param file           接收上传的文件对象
     * @param request        请求对象
     * @param abPath         存放的文件夹名称
     * @return               返回上传成功与否信息
     * @throws IOException
     */
    @Transactional
    public Map<String, Object> fileUpLoad(MultipartFile file, HttpServletRequest request, String abPath) throws IOException{
        Map<String, Object> map = new HashMap<>();
        String uploadPath = request.getServletContext().getRealPath("") + File.separator + abPath + File.separator;   // 得到相当项目工程的硬盘物理地址
        String fileName = file.getOriginalFilename();                   // 获取上传的文件名

        File newFile = new File(uploadPath + fileName);         // 根据当前工程的物理路径+文件名生成一个file对象
        FileCopyUtils.copy(file.getBytes(), newFile);                   // 通过上传通道中的字节码完成file文件的写入操作

        String path = newFile.toString();                                  // 获取文件的物理路径
        InputStream is = new FileInputStream(path);;                       // 生成输入流
        HSSFWorkbook workbook = new HSSFWorkbook(is);                      // HSSFWorkbook类型对应2003版本
        List<Dining> list = new ArrayList<>();
        Dining dining = null;
        Sheet sheet = workbook.getSheetAt(0);                       //模板数据默认读取第一个工作页sheet，index默认从0开始，如果存在多个sheet，那么需要循环Sheet判断

        for(int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);
            if (row.getCell(0) == null) {
                continue;
            }
            dining = new Dining();
            Cell cell = row.getCell(0);
            cell.setCellType(CellType.STRING);
            dining.setDiningType(cell.getStringCellValue());
            cell = row.getCell(1);
            cell.setCellType(CellType.STRING);
            dining.setDiningNumber(cell.getStringCellValue());
            cell = row.getCell(2);
            cell.setCellType(CellType.STRING);
            dining.setDiningCustomers(Integer.parseInt(cell.getStringCellValue()));
            cell = row.getCell(3);
            cell.setCellType(CellType.STRING);
            dining.setDiningAreaId(Integer.parseInt(cell.getStringCellValue()));
            list.add(dining);
        }
        diningMapper.insertAll(list);
        map.put("code", 200);
        map.put("msg", "上传用户数据成功");
//        map.put("data", userSQLPath);
        return map;
    }
}
