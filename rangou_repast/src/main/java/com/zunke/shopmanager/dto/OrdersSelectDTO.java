package com.zunke.shopmanager.dto;/**
* @author ：EdgeYu
* @date 2021-09-23 11:38
* @version ：1.0
*/

/**
 * @ClassName OrdersSelectDTO
 * @Date 2021/9/23 11:38
 * @Author 何永波
 * @Description TODO
*/
public class OrdersSelectDTO {
    private Integer dishesId;
    private String  dishesName;
    private Integer dishesNumber;
    private Double  dishesCount;
    private String  dishesPhoto;
    private Integer comboId;
    private String  comboName;
    private Integer comboNumber;
    private Double  comboCount;
    private String  comboPhoto;

    public String getDishesPhoto() {
        return dishesPhoto;
    }

    public void setDishesPhoto(String dishesPhoto) {
        this.dishesPhoto = dishesPhoto;
    }

    public String getComboPhoto() {
        return comboPhoto;
    }

    public void setComboPhoto(String comboPhoto) {
        this.comboPhoto = comboPhoto;
    }

    public Integer getDishesId() {
        return dishesId;
    }

    public void setDishesId(Integer dishesId) {
        this.dishesId = dishesId;
    }

    public String getDishesName() {
        return dishesName;
    }

    public void setDishesName(String dishesName) {
        this.dishesName = dishesName;
    }

    public Integer getDishesNumber() {
        return dishesNumber;
    }

    public void setDishesNumber(Integer dishesNumber) {
        this.dishesNumber = dishesNumber;
    }

    public Double getDishesCount() {
        return dishesCount;
    }

    public void setDishesCount(Double dishesCount) {
        this.dishesCount = dishesCount;
    }

    public Integer getComboId() {
        return comboId;
    }

    public void setComboId(Integer comboId) {
        this.comboId = comboId;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public Integer getComboNumber() {
        return comboNumber;
    }

    public void setComboNumber(Integer comboNumber) {
        this.comboNumber = comboNumber;
    }

    public Double getComboCount() {
        return comboCount;
    }

    public void setComboCount(Double comboCount) {
        this.comboCount = comboCount;
    }
}
