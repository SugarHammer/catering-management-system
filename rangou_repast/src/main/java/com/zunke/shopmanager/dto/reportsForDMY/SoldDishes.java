package com.zunke.shopmanager.dto.reportsForDMY;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * 菜品销售情况表(TempSoldDishes)实体类
 *
 * @author makejava
 * @since 2021-09-16 17:13:09
 * @version 1.0
 */
@ApiModel(value = "TempSoldDishes",description = "菜品销售情况表")
public class SoldDishes implements Serializable,Comparable<SoldDishes>{
    private static final long serialVersionUID = -32365663509144085L;
    /**
    * 主键id
    */
	@ApiModelProperty(name = "id",notes = "主键id",dataType = "Integer",required = true)
    private Integer id;
    /**
    * 菜品名称
    */
	@ApiModelProperty(name = "dishesNameBat",notes = "菜品名称",dataType = "String",required = true)
    private String dishesNameBat;
    /**
    * 套菜内卖出量
    */
	@ApiModelProperty(name = "dishesincombo",notes = "套菜内卖出量",dataType = "Integer",required = true)
    private Integer dishesincombo;
    /**
    * 单品卖出量
    */
	@ApiModelProperty(name = "dishesindishes",notes = "单品卖出量",dataType = "Integer",required = true)
    private Integer dishesindishes;
    /**
    * 销售总数量
    */
	@ApiModelProperty(name = "soldNum",notes = "销售总数量",dataType = "Integer",required = true)
    private Integer soldNum;
    /**
    * 销售总额
    */
	@ApiModelProperty(name = "soldSum",notes = "销售总额",dataType = "Double",required = true)
    private Double soldSum;

        
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
        
    public String getDishesNameBat() {
        return dishesNameBat;
    }

    public void setDishesNameBat(String dishesNameBat) {
        this.dishesNameBat = dishesNameBat;
    }
        
    public Integer getDishesincombo() {
        return dishesincombo;
    }

    public void setDishesincombo(Integer dishesincombo) {
        this.dishesincombo = dishesincombo;
    }
        
    public Integer getDishesindishes() {
        return dishesindishes;
    }

    public void setDishesindishes(Integer dishesindishes) {
        this.dishesindishes = dishesindishes;
    }
        
    public Integer getSoldNum() {
        return soldNum;
    }

    public void setSoldNum(Integer soldNum) {
        this.soldNum = soldNum;
    }
        
    public Double getSoldSum() {
        return soldSum;
    }

    public void setSoldSum(Double soldSum) {
        this.soldSum = soldSum;
    }

    @Override
    public int compareTo(@NotNull SoldDishes o) {
        if(soldNum != o.getSoldNum()){
            return o.getSoldNum() - soldNum;
        } else {
            return o.getDishesNameBat().length() - dishesNameBat.length();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SoldDishes) {
            SoldDishes tsd = (SoldDishes) obj;
            if ((soldNum == tsd.getSoldNum()) && (dishesNameBat.length() == tsd.getDishesNameBat().length())) {
                return true;
            } else {
                return true;
            }
        } else{
            return false;
        }
    }

}