package com.zunke.shopmanager.service.impl;

import com.zunke.shopmanager.mapper.*;
import com.zunke.shopmanager.pojo.Categories;
import com.zunke.shopmanager.pojo.Dishes;
import com.zunke.shopmanager.pojo.Subcategories;
import com.zunke.shopmanager.pojo.Unit;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UploadServiceImpl {
    @Resource
    private DishesMapper dishesMapper;
    @Resource
    private SubcategoriesMapper subcategoriesMapper;
    @Resource
    private CategoriesMapper categoriesMapper;
    @Resource
    private UnitMapper unitMapper;
    @Resource
    private ComboMapper comboMapper;

    public Map<String, Object> uploadPhoto(MultipartFile file, String id, HttpServletRequest request) throws IOException, FileUploadException, SQLException, ClassNotFoundException {
        Map<String, Object> map = new HashMap<>();
        String abPath = "upload/photo";
        String uploadPath = request.getServletContext().getRealPath("") + File.separator + abPath + File.separator;
        String fileName = file.getOriginalFilename();
        File newFile = new File(uploadPath + fileName);
        FileCopyUtils.copy(file.getBytes(), newFile);
//        User user = (User)request.getSession().getAttribute("user");
        String userSQLPath = File.separator + abPath + File.separator + fileName;
        dishesMapper.updatePhoto(userSQLPath, id);
        map.put("code", 200);
        map.put("msg", "上传图片成功");
        map.put("data", userSQLPath);
        return map;
    }
    public Map<String, Object> uploadComboPhoto(MultipartFile file, String id, HttpServletRequest request) throws IOException, FileUploadException, SQLException, ClassNotFoundException {
        Map<String, Object> map = new HashMap<>();
        String abPath = "upload/photo";
        String uploadPath = request.getServletContext().getRealPath("") + File.separator + abPath + File.separator;
        String fileName = file.getOriginalFilename();
        File newFile = new File(uploadPath + fileName);
        FileCopyUtils.copy(file.getBytes(), newFile);
//        User user = (User)request.getSession().getAttribute("user");
        String userSQLPath = File.separator + abPath + File.separator + fileName;
        subcategoriesMapper.updateComboPhoto(userSQLPath, id);
        map.put("code", 200);
        map.put("msg", "上传图片成功");
        map.put("data", userSQLPath);
        return map;
    }


    @Transactional
    public Map<String, Object> uploadExcel(MultipartFile file, HttpServletRequest request) throws IOException, FileUploadException, SQLException, ClassNotFoundException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        String abPath = "upload/excel";
        String uploadPath = request.getServletContext().getRealPath("") + File.separator + abPath + File.separator;
        String fileName = file.getOriginalFilename();
        File newFile = new File(uploadPath + fileName);
        String path = newFile.toString();
        FileCopyUtils.copy(file.getBytes(), newFile);
        String userSQLPath = File.separator + abPath + File.separator + fileName;


        String fileType = path.substring(path.lastIndexOf(".") + 1);   // 获取文件的后缀名
        List<Dishes> list = new ArrayList<>();          // 里面的list代表每一行数据，外面list代表所有行数据，实际项目中，需要把excel中的每一行数据做成POJO对象处理
        InputStream is = null;                                             // 生成输入流
        is = new FileInputStream(path);
        Workbook wb = null;
        if (fileType.equals("xls")) {                  // 判断是2003版本还是2007之后的版本，xls为2003版本，xlsx为2007版本
            wb = new HSSFWorkbook(is);                 // HSSFWorkbook类型对应2003版本
        } else if (fileType.equals("xlsx")) {
            wb = new XSSFWorkbook(is);                 // XSSFWorkbook类型对应2007之后版本
        } else {
            return null;
        }
//            User user = null;
        Sheet sheet = wb.getSheetAt(0);             //假设读取第一个工作页sheet，index默认从0开始，如果存在多个sheet，那么需要循环Sheet判断
        Class<Dishes> dishesClass = Dishes.class;
        Dishes dishes = null;
        Field[] fields = dishesClass.getDeclaredFields();

        for(int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null || row.getCell(0) == null || row.getCell(1) == null || row.getCell(2) == null || row.getCell(3) == null || row.getCell(4) == null || row.getCell(5) == null || row.getCell(6) == null){
               break;
            }
            dishes = new Dishes();
            Cell cell = row.getCell(0);
            cell.setCellType(CellType.STRING);
            dishes.setDishesNo(cell.getStringCellValue());
            cell = row.getCell(1);
            cell.setCellType(CellType.STRING);
            dishes.setDishesName(cell.getStringCellValue());
            cell = row.getCell(2);
            cell.setCellType(CellType.STRING);
            dishes.setDishesShortCode(cell.getStringCellValue());
            cell = row.getCell(3);
            cell.setCellType(CellType.STRING);
            dishes.setDishesPrice(Double.parseDouble(cell.getStringCellValue()));
            cell = row.getCell(4);
            cell.setCellType(CellType.STRING);
            Categories categories = categoriesMapper.selectByName(cell.getStringCellValue());
            int categoriesId = 0;
            if(categories == null){
                categories = new Categories();
                categories.setCategoriesName(cell.getStringCellValue());
                categoriesMapper.insert(categories);
                categoriesId = categoriesMapper.selectByName(cell.getStringCellValue()).getId();
            }
            else{
                categoriesId = categories.getId();
            }
            cell = row.getCell(5);
            cell.setCellType(CellType.STRING);
            Subcategories subcategories = subcategoriesMapper.selectByNameAndCategoriesId(cell.getStringCellValue(), categoriesId);
            Integer subcategoriesId = 0;
            if (subcategories == null){
                subcategories = new Subcategories();
                subcategories.setSubcategoriesName(cell.getStringCellValue());
                subcategories.setCategoriesId(categoriesId);
                subcategoriesMapper.insert(subcategories);
                subcategoriesId = subcategoriesMapper.selectByNameAndCategoriesId(cell.getStringCellValue(), categoriesId).getSubcategoriesOrder();
            }else{
                subcategoriesId = subcategories.getCategoriesId();
            }
            dishes.setSubcategoriesId(subcategoriesId);
            cell = row.getCell(6);
            cell.setCellType(CellType.STRING);
            Unit unit = unitMapper.selectByName(cell.getStringCellValue());
            Integer unitId = 0;
            if (unit == null){
                unit = new Unit();
                unit.setUnitName(cell.getStringCellValue());
                unitId = unitMapper.selectByName(cell.getStringCellValue()).getId();
            }else{
                unitId = unit.getId();
            }
            dishes.setUnitId(unitId);
            list.add(dishes);
        }

                if (is != null) {
                    is.close();
                }

//        return lists;
        System.out.println(list);

        dishesMapper.insertAll(list);
        map.put("code", 200);
        map.put("msg", "上传数据成功");
        return map;
    }
}
