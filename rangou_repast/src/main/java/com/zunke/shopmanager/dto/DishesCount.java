package com.zunke.shopmanager.dto;

public class DishesCount {
    private Integer id;
    private String categoriesName;
    private Integer dishesCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public Integer getDishesCount() {
        return dishesCount;
    }

    public void setDishesCount(Integer dishesCount) {
        this.dishesCount = dishesCount;
    }
}
