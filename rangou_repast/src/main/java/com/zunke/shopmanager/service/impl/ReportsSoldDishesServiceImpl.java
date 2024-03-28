package com.zunke.shopmanager.service.impl;

import com.zunke.shopmanager.dto.reportsForDMY.SoldDishes;
import com.zunke.shopmanager.mapper.ReportsDishesMapper;
import com.zunke.shopmanager.mapper.ReportsSoldDishesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yiming
 * @version 1.0
 * @since 2021/9/16 9:00
 */
@Service
public class ReportsSoldDishesServiceImpl {
    @Resource
    private ReportsSoldDishesMapper reportsSoldDishesMapper;

    @Resource
    private ReportsDishesMapper reportsDishesMapper;

    public Map<String, Object> selectForDishesSold(int index,String dishesName) {
        List<String> listOfDishesNames = reportsDishesMapper.selectDishesNames(dishesName);
        List<SoldDishes> soldDishesObjects = new ArrayList<>();
        for(int i = 0;i < listOfDishesNames.size();i++){
            SoldDishes soldDishes = new SoldDishes();
            System.out.println(listOfDishesNames.get(i));
            // 0.获取菜品的id
            int dishesId = reportsDishesMapper.selectIdByDishesName(listOfDishesNames.get(i));
            // 1.获取套菜内该菜品的数量
            int dishesInComboCount = reportsSoldDishesMapper.dishesInComboSelectForCount(listOfDishesNames.get(i));
            // 2.获取单点菜品的数量
            int dishesInDishesCount = reportsSoldDishesMapper.dishesInDishesSelectForCount(listOfDishesNames.get(i));
            // 3.总售出
            int soldNum = dishesInComboCount + dishesInDishesCount;
            double price = reportsDishesMapper.selectDishesPriceByDishesName(listOfDishesNames.get(i));
            // 4.总收入
            BigDecimal price_b = new BigDecimal(Double.toString(price));
            BigDecimal soldNum_b = new BigDecimal(Integer.toString(soldNum));
            double soldSum = soldNum_b.multiply(price_b).doubleValue();

            soldDishes.setId(dishesId);
            soldDishes.setDishesNameBat(listOfDishesNames.get(i));
            soldDishes.setDishesincombo(dishesInComboCount);
            soldDishes.setDishesindishes(dishesInDishesCount);
            soldDishes.setSoldNum(soldNum);
            soldDishes.setSoldSum(soldSum);
            soldDishesObjects.add(soldDishes);
        }

        int pageCount = (listOfDishesNames.size() - 1) / 10 + 1;
        index = (index - 1) * 10;
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("msg", "查询成功");
        map.put("pageCount", pageCount);  // 查询的记录总页码
        map.put("count", listOfDishesNames.size());     // 当前表中的总条数
        map.put("data", soldDishesObjects);
        return map;
    }
}
