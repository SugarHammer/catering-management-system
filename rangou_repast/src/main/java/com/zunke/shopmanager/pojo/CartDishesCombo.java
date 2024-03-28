package com.zunke.shopmanager.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 购物车菜品套菜中间表(CartDishesCombo)实体类
 *
 * @author Hernie
 * @since 2021-09-22 14:39:19
 */

@ApiModel(value = "CartDishesCombo", description = "购物车菜品套菜中间表")
public class CartDishesCombo implements Serializable {
    private static final long serialVersionUID = -85955004153927282L;
    /**
     * 购物车菜品套菜中间表主键
     */
    @ApiModelProperty(name = "id", notes = "购物车菜品套菜中间表主键", dataType = "Integer", required = true)
    private Integer id;
    /**
     * 购物车主键
     */
    @ApiModelProperty(name = "cartId", notes = "购物车主键", dataType = "Integer", required = true)
    private Integer cartId;
    /**
     * 菜品主键
     */
    @ApiModelProperty(name = "dishesId", notes = "菜品主键", dataType = "Integer", required = true)
    private Integer dishesId;
    /**
     * 套菜主键
     */
    @ApiModelProperty(name = "camboId", notes = "套菜主键", dataType = "Integer", required = true)
    private Integer comboId;
    /**
     * 菜品数量
     */
    @ApiModelProperty(name = "dishesNumber", notes = "菜品数量", dataType = "Integer", required = true)
    private Integer dishesNumber;
    /**
     * 套菜数量
     */
    @ApiModelProperty(name = "comboNumber", notes = "套菜数量", dataType = "Integer", required = true)
    private Integer comboNumber;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
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

    public Integer getDishesNumber() {
        return dishesNumber;
    }

    public void setDishesNumber(Integer dishesNumber) {
        this.dishesNumber = dishesNumber;
    }

    public Integer getComboNumber() {
        return comboNumber;
    }

    public void setComboNumber(Integer comboNumber) {
        this.comboNumber = comboNumber;
    }

}
