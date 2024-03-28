package com.zunke.shopmanager.dto;

import com.zunke.shopmanager.pojo.Dishes;

import java.util.List;

// select c.id as c2id, combo_no, combo_price, unit_id, combo_name, combo_photo,
//        u.id, unit_name, unit_type, unit_default_amount,
//        cdc.id, cart_id, dishes_id, combo_id, dishes_number, combo_number
//        from zunkeu8.combo c left join unit u on c.unit_id=u.id left join cart_dishes_combo cdc on c.id = cdc.combo_id
//        where cart_id = #{cartId}
public class CartCombo {
    private Integer id;
    private String comboName;
    private String comboNo;
    private Double comboPrice;
    private String comboPhoto;
    private String unitName;
    private Integer dishesNumber;
    private List<Dishes> dishesList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public String getComboNo() {
        return comboNo;
    }

    public void setComboNo(String comboNo) {
        this.comboNo = comboNo;
    }

    public Double getComboPrice() {
        return comboPrice;
    }

    public void setComboPrice(Double comboPrice) {
        this.comboPrice = comboPrice;
    }

    public String getComboPhoto() {
        return comboPhoto;
    }

    public void setComboPhoto(String comboPhoto) {
        this.comboPhoto = comboPhoto;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getDishesNumber() {
        return dishesNumber;
    }

    public void setDishesNumber(Integer dishesNumber) {
        this.dishesNumber = dishesNumber;
    }

    public List<Dishes> getDishesList() {
        return dishesList;
    }

    public void setDishesList(List<Dishes> dishesList) {
        this.dishesList = dishesList;
    }
}
