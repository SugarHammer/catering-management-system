package com.zunke.shopmanager.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author yuzhe
 * @version 1.0
 * @since 2021-09-16-14:24
 */
public class DishesInfoDTO implements Serializable {

    private static final long serialVersionUID = -5097260885634128790L;

    /**
     * 菜品信息id
     */
    @ApiModelProperty(name = "id",notes = "菜品信息id",dataType = "Integer",required = true)
    private Integer id;

    /**
     * 菜品名称
     */
    @ApiModelProperty(name = "dishesName",notes = "菜品名称",dataType = "String",required = true)
    private String dishesName;

    /**
     * 菜品价格
     */
    @ApiModelProperty(name = "dishesPrice",notes = "菜品价格",dataType = "Double",required = true)
    private Double dishesPrice;

    /**
     * 数量
     */
    @ApiModelProperty(name = "dishesNum",notes = "数量",dataType = "Integer",required = true)
    private Integer dishesNum;


    public Integer getId() {
        return id;
    }

    public DishesInfoDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getDishesName() {
        return dishesName;
    }

    public DishesInfoDTO setDishesName(String dishesName) {
        this.dishesName = dishesName;
        return this;
    }

    public Double getDishesPrice() {
        return dishesPrice;
    }

    public DishesInfoDTO setDishesPrice(Double dishesPrice) {
        this.dishesPrice = dishesPrice;
        return this;
    }

    public Integer getDishesNum() {
        return dishesNum;
    }

    public DishesInfoDTO setDishesNum(Integer dishesNum) {
        this.dishesNum = dishesNum;
        return this;
    }

}
