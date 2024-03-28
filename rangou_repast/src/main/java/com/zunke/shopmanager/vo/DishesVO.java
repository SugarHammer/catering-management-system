package com.zunke.shopmanager.vo;

import com.zunke.shopmanager.pojo.Recipe;

import java.util.List;

public class DishesVO {
    private Integer id;
    private String dishesNo;
    private String dishesName;
    private Integer dishesBulk;
    private String dishesShortCode;
    private Double dishesPrice;
    private String dishesPhoto;
    private Integer dishesAction;
    private String unitName;
    private String subcategoriesName;
    private String categoriesName;
    private List<Recipe> recipeList;

    public Integer getDishesAction() {
        return dishesAction;
    }

    public void setDishesAction(Integer dishesAction) {
        this.dishesAction = dishesAction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDishesNo() {
        return dishesNo;
    }

    public void setDishesNo(String dishesNo) {
        this.dishesNo = dishesNo;
    }

    public String getDishesName() {
        return dishesName;
    }

    public void setDishesName(String dishesName) {
        this.dishesName = dishesName;
    }

    public Integer getDishesBulk() {
        return dishesBulk;
    }

    public void setDishesBulk(Integer dishesBulk) {
        this.dishesBulk = dishesBulk;
    }

    public String getDishesShortCode() {
        return dishesShortCode;
    }

    public void setDishesShortCode(String dishesShortCode) {
        this.dishesShortCode = dishesShortCode;
    }

    public Double getDishesPrice() {
        return dishesPrice;
    }

    public void setDishesPrice(Double dishesPrice) {
        this.dishesPrice = dishesPrice;
    }

    public String getDishesPhoto() {
        return dishesPhoto;
    }

    public void setDishesPhoto(String dishesPhoto) {
        this.dishesPhoto = dishesPhoto;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getSubcategoriesName() {
        return subcategoriesName;
    }

    public void setSubcategoriesName(String subcategoriesName) {
        this.subcategoriesName = subcategoriesName;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }
}
