package com.zunke.shopmanager.pojo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 餐桌表(Dining)实体类
 *
 * @author ZhangHongNeng
 * @since 2021-09-13 11:20:03
 * @version 1.0
 */
@ApiModel(value = "Dining",description = "餐桌表")
public class Dining implements Serializable {
    private static final long serialVersionUID = 307400689903945395L;
    /**
    * 餐桌主键
    */
	@ApiModelProperty(name = "id",notes = "餐桌主键",dataType = "Integer",required = true)
    private Integer id;
    /**
    * 所属类型（大厅、包间、单间）
    */
	@ApiModelProperty(name = "diningType",notes = "所属类型（大厅、包间、单间）",dataType = "String",required = true)
    private String diningType;
    /**
    * 餐桌编号
    */
	@ApiModelProperty(name = "diningNumber",notes = "餐桌编号",dataType = "String",required = true)
    private String diningNumber;
    /**
    * 人数
    */
	@ApiModelProperty(name = "diningCustomers",notes = "人数",dataType = "Integer",required = true)
    private Integer diningCustomers;
    /**
    * 餐桌当前状态（0空闲、1用餐、2待打扫）
    */
	@ApiModelProperty(name = "diningAction",notes = "餐桌当前状态（0空闲、1用餐、2待打扫）",dataType = "Integer",required = true)
    private int diningAction;
    /**
    * 餐桌区域id
    */
	@ApiModelProperty(name = "diningAreaId",notes = "餐桌区域id",dataType = "Integer",required = true)
    private int diningAreaId;

	private List<Dining> diningList;

	private DiningArea diningArea;

    public void setDiningAction(int diningAction) {
        this.diningAction = diningAction;
    }

    public void setDiningAreaId(int diningAreaId) {
        this.diningAreaId = diningAreaId;
    }

    public List<Dining> getDiningList() {
        return diningList;
    }

    public void setDiningList(List<Dining> diningList) {
        this.diningList = diningList;
    }

    public DiningArea getDiningArea() {
        return diningArea;
    }

    public void setDiningArea(DiningArea diningArea) {
        this.diningArea = diningArea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
        
    public String getDiningType() {
        return diningType;
    }

    public void setDiningType(String diningType) {
        this.diningType = diningType;
    }
        
    public String getDiningNumber() {
        return diningNumber;
    }

    public void setDiningNumber(String diningNumber) {
        this.diningNumber = diningNumber;
    }
        
    public Integer getDiningCustomers() {
        return diningCustomers;
    }

    public void setDiningCustomers(Integer diningCustomers) {
        this.diningCustomers = diningCustomers;
    }
        
    public Integer getDiningAction() {
        return diningAction;
    }

    public void setDiningAction(Integer diningAction) {
        this.diningAction = diningAction;
    }
        
    public Integer getDiningAreaId() {
        return diningAreaId;
    }

    public void setDiningAreaId(Integer diningAreaId) {
        this.diningAreaId = diningAreaId;
    }

    @Override
    public String toString() {
        return "Dining{" +
                "id=" + id +
                ", diningType='" + diningType + '\'' +
                ", diningNumber='" + diningNumber + '\'' +
                ", diningCustomers=" + diningCustomers +
                ", diningAction=" + diningAction +
                ", diningAreaId=" + diningAreaId +
                ", diningList=" + diningList +
                ", diningArea=" + diningArea +
                '}';
    }
}