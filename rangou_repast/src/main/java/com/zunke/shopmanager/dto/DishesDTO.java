package com.zunke.shopmanager.dto;

import com.zunke.shopmanager.pojo.Recipe;

import java.util.List;

public class DishesDTO {
    //d.id, dishes_no, dishes_name, dishes_bulk, dishes_short_code, dishes_price, dishes_photo,
    //        subcategories_name, categories_name,
    //        unit_name,
    //        rid, recipe_no, recipe_explain, recipe_type, recipe_markup, recipe_markup_by_amount, recipe_short_code
    private Integer id;
    private String dishesNo;
    private String dishesName;
    private Integer dishesBulk;
    private String dishesShortCode;
    private Double dishesPrice;
    private String dishesPhoto;
    private String unitName;
    private Integer unitId;
    private Integer subcategoriesId;
    private String subcategoriesName;
    private Integer dishesAction;
    private String categoriesName;
    private Integer dishesComboNumber;
    private Integer dishesNumber;
    private List<Recipe> recipeList;

    public Integer getDishesNumber() {
        return dishesNumber;
    }

    public void setDishesNumber(Integer dishesNumber) {
        this.dishesNumber = dishesNumber;
    }

    public Integer getDishesAction() {
        return dishesAction;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public Integer getDishesComboNumber() {
        return dishesComboNumber;
    }

    public void setDishesComboNumber(Integer dishesComboNumber) {
        this.dishesComboNumber = dishesComboNumber;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Integer getSubcategoriesId() {
        return subcategoriesId;
    }

    public void setSubcategoriesId(Integer subcategoriesId) {
        this.subcategoriesId = subcategoriesId;
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
