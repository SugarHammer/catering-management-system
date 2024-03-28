package com.zunke.shopmanager.mapper;

import org.apache.ibatis.annotations.Param;

public interface ReportsSoldDishesMapper {
    /**
     * @Author Yiming
     * @Description 套菜内单个菜品总个数
     * @Date 12:22 2021/9/16
     * @Param [dishesName]
     * @return int
     **/
    int dishesInComboSelectForCount(@Param("dishesName") String dishesName);

    int dishesInDishesSelectForCount(@Param("dishesName") String dishesName);

}
