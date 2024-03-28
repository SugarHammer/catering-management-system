package com.zunke.shopmanager.dto;

import com.zunke.shopmanager.pojo.Cart;
import com.zunke.shopmanager.pojo.Combo;
import com.zunke.shopmanager.pojo.Dishes;

import java.util.List;

public class CartDTO {
    private Integer id;
    private String cartNo;
    private Integer cartAction;
    private Integer diningId;
    private Integer mumberId;
    private Double cartPrice;
    private List<Dishes> dishesList;
    private List<Combo> comboList;
    private List<Cart> cartDishesComboList;

    public List<Dishes> getDishesList() {
        return dishesList;
    }

    public void setDishesList(List<Dishes> dishesList) {
        this.dishesList = dishesList;
    }

    public List<Combo> getComboList() {
        return comboList;
    }

    public void setComboList(List<Combo> comboList) {
        this.comboList = comboList;
    }

    public List<Cart> getCartDishesComboList() {
        return cartDishesComboList;
    }

    public void setCartDishesComboList(List<Cart> cartDishesComboList) {
        this.cartDishesComboList = cartDishesComboList;
    }

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
