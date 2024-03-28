package com.zunke.shopmanager.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 菜品大类(Categories)实体类
 *
 * @author Hernie
 * @since 2021-09-12 16:26:33
 */

@ApiModel(value = "Categories", description = "菜品大类")
public class Categories implements Serializable {
    private static final long serialVersionUID = -34067041439759052L;
    /**
     * 菜品大类主键
     */
    @ApiModelProperty(name = "id", notes = "菜品大类主键", dataType = "Integer", required = true)
    private Integer id;
    /**
     * 菜品大类名称
     */
    @ApiModelProperty(name = "categoriesName", notes = "菜品大类名称", dataType = "String", required = true)
    private String categoriesName;

    private List<Subcategories> subcategoriesList;

    public List<Subcategories> getSubcategoriesList() {
        return subcategoriesList;
    }

    public void setSubcategoriesList(List<Subcategories> subcategoriesList) {
        this.subcategoriesList = subcategoriesList;
    }

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

}
