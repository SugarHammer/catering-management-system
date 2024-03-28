package com.zunke.shopmanager.pojo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 权限表(Permission)实体类
 *
 * @author ZhangHongNeng
 * @since 2021-09-16 17:11:41
 * @version 1.0
 */
@ApiModel(value = "Permission",description = "权限表")
public class Permission implements Serializable {
    private static final long serialVersionUID = 160486211640618912L;
    /**
    * 权限id
    */
	@ApiModelProperty(name = "id",notes = "权限id",dataType = "Integer",required = true)
    private Integer id;
    /**
    * 权限名称
    */
	@ApiModelProperty(name = "permissionName",notes = "权限名称",dataType = "String",required = true)
    private String permissionName;

        
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
        
    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

}