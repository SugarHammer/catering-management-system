package com.zunke.shopmanager.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 菜品套菜关联表(DishesCombo)实体类
 *
 * @author Hernie
 * @since 2021-09-15 10:24:00
 */

@ApiModel(value = "DishesCombo", description = "菜品套菜关联表")
public class DishesCombo implements Serializable {
    private static final long serialVersionUID = -72540156748227299L;
    /**
     * 菜品套菜主键
     */
    @ApiModelProperty(name = "id", notes = "菜品套菜主键", dataType = "Integer", required = true)
    private Integer id;
    /**
     * 菜品主键
     */
    @ApiModelProperty(name = "dishesId", notes = "菜品主键", dataType = "Integer", required = true)
    private Integer dishesId;
    /**
     * 套菜主键
     */
    @ApiModelProperty(name = "comboId", notes = "套菜主键", dataType = "Integer", required = true)
    private Integer comboId;

    private Integer dishesComboNumber;

    public Integer getDishesComboNumber() {
        return dishesComboNumber;
    }

    public void setDishesComboNumber(Integer dishesComboNumber) {
        this.dishesComboNumber = dishesComboNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDishesId() {
        return dishesId;
    }

    public void setDishesId(Integer dishesId) {
        this.dishesId = dishesId;
    }

    public Integer getComboId() {
        return comboId;
    }

    public void setComboId(Integer comboId) {
        this.comboId = comboId;
    }

}
