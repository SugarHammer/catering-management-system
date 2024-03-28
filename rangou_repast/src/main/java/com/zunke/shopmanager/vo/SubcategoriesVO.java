package com.zunke.shopmanager.vo;

public class SubcategoriesVO {
    private Integer id;

    private String subcategoriesNo;
    private String subcategoriesName;
    private String subcategoriesColor;
    private String categoriesName;
    private Integer subcategoriesOrder;
//    private Integer index;
//    private Integer limit;

//    public Integer getIndex() {
//        return index;
//    }
//
//    public void setIndex(Integer index) {
//        this.index = index;
//    }
//
//    public Integer getLimit() {
//        return limit;
//    }
//
//    public void setLimit(Integer limit) {
//        this.limit = limit;
//    }

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
