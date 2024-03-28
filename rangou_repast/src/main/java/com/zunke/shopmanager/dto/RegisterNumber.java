package com.zunke.shopmanager.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName RegisterNumber
 * @Date 2021/9/16 14:34
 * @Author 何永波
 * @Description TODO
 */
@ApiModel(value = "RegisterNumber", description = "注册人数dto")
public class RegisterNumber {
    @ApiModelProperty(name = "id", notes = "主键id", dataType = "Integer", required = true)
    private int id;
    @ApiModelProperty(name = "roleId", notes = "角色id", dataType = "Integer", required = true)
    private int roleId;

    @ApiModelProperty(name = "rergistrationTime", notes = "会员注册时间", dataType = "String", required = true)
    private String rergistrationTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRergistrationTime() {
        return rergistrationTime;
    }

    public void setRergistrationTime(String rergistrationTime) {
        this.rergistrationTime = rergistrationTime;
    }
}