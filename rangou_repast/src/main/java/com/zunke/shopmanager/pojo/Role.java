package com.zunke.shopmanager.pojo;

import java.io.Serializable;
import java.util.List;

import com.zunke.shopmanager.dto.MumberRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 角色表(Role)实体类
 *
 * @author ZhangHongNeng
 * @since 2021-09-16 19:27:35
 * @version 1.0
 */
@ApiModel(value = "Role",description = "角色表")
public class Role implements Serializable {
    private static final long serialVersionUID = -83659509407310304L;
    /**
    * 角色id
    */
	@ApiModelProperty(name = "id",notes = "角色id",dataType = "Integer",required = true)
    private Integer id;
    /**
    * 角色名称
    */
	@ApiModelProperty(name = "roleName",notes = "角色名称",dataType = "String",required = true)
    private String roleName;
    /**
    * 权限的id
    */
	@ApiModelProperty(name = "permissionId",notes = "权限的id",dataType = "Integer",required = true)
    private Integer permissionId;

    private List<MumberRole> mumberRoleList;

    private List<Permission> permissionList;

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public List<MumberRole> getMumberRoleList() {
        return mumberRoleList;
    }

    public void setMumberRoleList(List<MumberRole> mumberRoleList) {
        this.mumberRoleList = mumberRoleList;
    }

        
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
        
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
        
    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

}