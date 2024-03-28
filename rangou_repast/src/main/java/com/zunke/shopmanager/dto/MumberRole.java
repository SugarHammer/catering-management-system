package com.zunke.shopmanager.dto;

import com.zunke.shopmanager.pojo.Permission;

import java.util.List;

/**
 * @author ：ZhangHongNeng
 * @version ：1.0
 * @since 2021-09-13 15:15
 */
public class MumberRole {
    private Integer id;
    private String mumberName;
    private String mumberPassword;
    private String mumberPhone;
    private String roleName;
    private String permissionName;
    private Integer roleId;
    private List<Permission> permissionList;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMumberName() {
        return mumberName;
    }

    public void setMumberName(String mumberName) {
        this.mumberName = mumberName;
    }

    public String getMumberPassword() {
        return mumberPassword;
    }

    public void setMumberPassword(String mumberPassword) {
        this.mumberPassword = mumberPassword;
    }

    public String getMumberPhone() {
        return mumberPhone;
    }

    public void setMumberPhone(String mumberPhone) {
        this.mumberPhone = mumberPhone;
    }
}
