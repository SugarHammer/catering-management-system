package com.zunke.shopmanager.dto;

public class SubcategoriesDTO {
    // c.id, categories_name,
    //               s.id, subcategories_no, subcategories_name, subcategories_color, subcategories_order, categories_id
    private Integer id;
    private String subcategoriesNo;
    private String subcategoriesName;
    private String subcategoriesColor;
    private String categoriesName;
    private Integer subcategoriesOrder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubcategoriesNo() {
        return subcategoriesNo;
    }

    public void setSubcategoriesNo(String subcategoriesNo) {
        this.subcategoriesNo = subcategoriesNo;
    }

    public String getSubcategoriesName() {
        return subcategoriesName;
    }

    public void setSubcategoriesName(String subcategoriesName) {
        this.subcategoriesName = subcategoriesName;
    }

    public String getSubcategoriesColor() {
        return subcategoriesColor;
    }

    public void setSubcategoriesColor(String subcategoriesColor) {
        this.subcategoriesColor = subcategoriesColor;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public Integer getSubcategoriesOrder() {
        return subcategoriesOrder;
    }

    public void setSubcategoriesOrder(Integer subcategoriesOrder) {
        this.subcategoriesOrder = subcategoriesOrder;
    }
}
