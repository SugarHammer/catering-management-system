package com.zunke.shopmanager.service.impl;

import com.zunke.shopmanager.dto.reportsForDMY.SoldDishes;
import com.zunke.shopmanager.mapper.ReportsDishesMapper;
import com.zunke.shopmanager.mapper.ReportsSoldDishesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Yiming
 * @version 1.0
 * @since 2021/9/17 14:59
 */
@Service
public class ReportsRankingListServiceImpl {
    @Resource
    private ReportsSoldDishesMapper reportsSoldDishesMapper;

    @Resource
    private ReportsDishesMapper reportsDishesMapper;

    public Map<String, Object> selectForRankingList() {
        List<String> listOfDishesNames = reportsDishesMapper.selectAllDishesNames();
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

        Collections.sort(soldDishesObjects);

        List<String> deshesNamesList = new ArrayList<>();
        List<Integer> soldNumList = new ArrayList<>();
        // 设置排行榜显示数据i
        for(int i = 0; i < 3; i++) {
            SoldDishes soldDishes = soldDishesObjects.get(i);
            deshesNamesList.add(soldDishes.getDishesNameBat());
            soldNumList.add(soldDishes.getSoldNum());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("deshesNamesList", deshesNamesList);
        map.put("soldNumList", soldNumList);
        return map;
    }
}
