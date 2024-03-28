package com.zunke.shopmanager.pojo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 购物车(Cart)实体类
 *
 * @author Hernie
 * @since 2021-09-26 10:06:15
 */

@ApiModel(value = "Cart", description = "购物车")
public class Cart implements Serializable {
    private static final long serialVersionUID = -28533669184760282L;
    /**
     * 购物车主键
     */
    @ApiModelProperty(name = "id", notes = "购物车主键", dataType = "Integer", required = true)
    private Integer id;
    /**
     * 购物车编号
     */
    @ApiModelProperty(name = "cartNo", notes = "购物车编号", dataType = "String", required = true)
    private String cartNo;
    /**
     * 购物车状态（0为未提交，1为提交）
     */
    @ApiModelProperty(name = "cartAction", notes = "购物车状态（0为未提交，1为提交）", dataType = "Integer", required = true)
    private Integer cartAction;
    /**
     * 餐桌主键
     */
    @ApiModelProperty(name = "diningId", notes = "餐桌主键", dataType = "Integer", required = true)
    private Integer diningId;
    /**
     * 会员主键
     */
    @ApiModelProperty(name = "mumberId", notes = "会员主键", dataType = "Integer", required = true)
    private Integer mumberId;
    /**
     * 购物车总价
     */
    @ApiModelProperty(name = "cartPrice", notes = "购物车总价", dataType = "Double", required = true)
    private Double cartPrice;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCartNo() {
        return cartNo;
    }

    public void setCartNo(String cartNo) {
        this.cartNo = cartNo;
    }

    public Integer getCartAction() {
        return cartAction;
    }

    public void setCartAction(Integer cartAction) {
        this.cartAction = cartAction;
    }

    public Integer getDiningId() {
        return diningId;
    }

    public void setDiningId(Integer diningId) {
        this.diningId = diningId;
    }

    public Integer getMumberId() {
        return mumberId;
    }

    public void setMumberId(Integer mumberId) {
        this.mumberId = mumberId;
    }

    public Double getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(Double cartPrice) {
        this.cartPrice = cartPrice;
    }

}
