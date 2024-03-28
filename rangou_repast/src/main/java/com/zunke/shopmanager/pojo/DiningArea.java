package com.zunke.shopmanager.pojo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 餐桌区域表(DiningArea)实体类
 *
 * @author ZhangHongNeng
 * @since 2021-09-13 11:00:52
 * @version 1.0
 */
@ApiModel(value = "DiningArea",description = "餐桌区域表")
public class DiningArea implements Serializable {
    private static final long serialVersionUID = -70807780948572479L;
    /**
    * 餐桌区域主键
    */
	@ApiModelProperty(name = "id",notes = "餐桌区域主键",dataType = "Integer",required = true)
    private Integer id;
    /**
    * 餐桌区域名称
    */
	@ApiModelProperty(name = "diningAreaName",notes = "餐桌区域名称",dataType = "String",required = true)
    private String diningAreaName;
    /**
    * 餐桌区域编码
    */
	@ApiModelProperty(name = "diningAreaCoding",notes = "餐桌区域编码",dataType = "String",required = true)
    private String diningAreaCoding;

	private List<Dining> diningList;

    public List<Dining> getDiningList() {
        return diningList;
    }

    public void setDiningList(List<Dining> diningList) {
        this.diningList = diningList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
        
    public String getDiningAreaName() {
        return diningAreaName;
    }

    public void setDiningAreaName(String diningAreaName) {
        this.diningAreaName = diningAreaName;
    }
        
    public String getDiningAreaCoding() {
        return diningAreaCoding;
    }

    public void setDiningAreaCoding(String diningAreaCoding) {
        this.diningAreaCoding = diningAreaCoding;
    }

}