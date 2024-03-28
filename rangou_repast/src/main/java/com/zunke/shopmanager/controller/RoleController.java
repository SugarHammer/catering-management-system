package com.zunke.shopmanager.controller;

import com.zunke.shopmanager.dto.MumberRole;
import com.zunke.shopmanager.pojo.Role;
import com.zunke.shopmanager.service.impl.RoleServiceImpl;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author ：ZhangHongNeng
 * @version ：1.0
 * @since 2021-09-14 10:00
 */
@RestController
@RequestMapping("/role")
@Api(value = "角色表(role)管理",tags = "角色表(role)管理接口API")
@ApiResponses({
        @ApiResponse(code=400,message="请求参数不完整或者错误"),
        @ApiResponse(code=404,message="找不到页面"),
        @ApiResponse(code=405,message="请求方式不正确，比如后台接收是post，但前端请求的是get"),
        @ApiResponse(code=406,message="页面地址和接口名冲突"),
        @ApiResponse(code=415,message="请求格式不正确，前端可能没有把请求头修改为json，也可能前端请求的json对象没有转换为字符串"),
        @ApiResponse(code=500,message="后台服务逻辑错误")
})
public class RoleController {
    @Resource
    private RoleServiceImpl roleService;

    /**
     * 通过用户id查询单条数据
     *
     * @param id 用户主键
     * @return 实例对象
     */
    @RequestMapping(value = "/selectById", method = RequestMethod.GET)
    @ApiOperation(value = "通过id查询用户信息的接口",notes = "通过id查询用户信息的接口",httpMethod = "GET")
    public Map<String, Object> selectById(@RequestParam("id") Integer id) {
        return this.roleService.selectById(id);
    }



    /**
     * 查询所有数据
     * @return 返回所有数据
     */
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有数据接口",notes = "查询所有数据接口",httpMethod = "GET")
    public Map<String, Object> selectAll() {
        return this.roleService.selectAll();
    }

    /**
     * 修改权限
     * @param role  权限对象
     * @return      更新结果
     */
    @RequestMapping(value = "/updateByName", method = RequestMethod.POST)
    @ApiOperation(value = "通过角色名更新一个角色的接口",notes = "通过角色名更新一个角色的接口",httpMethod = "POST")
    public Map<String, Object> updateByName(@RequestBody @ApiParam(name = "role",value = "pojo模型",required = true) Role role) {
        return this.roleService.updateByName(role);
    }


    /**
     * 通过用户主键修改角色
     * @param roleId    权限主键
     * @param id        用户主键
     * @return          得到的
     */
    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    @ApiOperation(value = "通过用户名修改角色的接口",notes = "通过用户名修改角色的接口",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId",value = "用户角色主键",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "id",value = "用户主键",paramType = "query",dataType = "Integer",required = true)
    })
    public Map<String, Object> updateRole(Integer roleId,Integer id) {
        return this.roleService.updateRole(roleId,id);
    }

    /**
     * 修改用户资料
     * @param mumberRole    dto对象
     * @return              更新结果
     */
    @RequestMapping(value = "/updateByMumber", method = RequestMethod.POST)
    @ApiOperation(value = "修改用户资料的接口",notes = "修改用户资料的接口",httpMethod = "POST")
    public Map<String, Object> updateByMumber(@RequestBody @ApiParam(name = "mumber",value = "mumber模型",required = true) MumberRole mumberRole) {
        return this.roleService.updateByMumber(mumberRole);
    }

//    /**
//     * 修改用户资料
//     * @param mumberRole
//     * @return
//     */
//    @RequestMapping(value = "/updateByMumber", method = RequestMethod.POST)
//    @ApiOperation(value = "修改用户资料的接口",notes = "修改用户资料的接口",httpMethod = "POST")
//    public Map<String, Object> updateByMumber(@RequestBody @ApiParam(name = "mumber",value = "mumber模型",required = true) String mumberName, String mumberPhone,String mumberPassword,int id) {
//        return this.roleService.updateByMumber(mumberName,mumberPhone,mumberPassword,id);
//    }

    /**
     * 通过主键删除单条账户数据
     *
     * @param id    用户主键
     * @return      单条数据
     */
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ApiOperation(value = "根据一个主键删除一条**记录的接口",notes = "根据一个主键删除一条**记录的接口",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "主键",paramType = "query",dataType = "String",required = true),
    })
    public Map<String, Object> deleteById(@RequestParam("id") String id) {
        return this.roleService.deleteById(id);
    }

    /**
     * 通过主键查询单条数据
     * @param index     需要查询的页码
     * @param name      模糊查询的内容
     * @return          查询到的数据
     */
    @RequestMapping(value = "/selectForPage", method = RequestMethod.GET)
    @ApiOperation(value = "列表查询分页接口",notes = "列表查询分页接口",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页码",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "name",value = "**名称",paramType = "query",dataType = "String",required = false)
    })
    public Map<String, Object> selectForPage(@RequestParam(name = "page") int index, String name) {
        return this.roleService.selectForPage(index, name);
    }

    /**
     * 通过一个dto对象新增单条数据
     *
     * @param mumberRole 一个数据库对应的dto数据对象
     * @return 返回插入的主键id
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "根据完整记录新增一个用户的接口",notes = "根据完整记录新增一个**的接口",httpMethod = "POST")
    public Map<String, Object> insert(@RequestBody @ApiParam(name = "mumberRole",value = "dto模型",required = true) MumberRole mumberRole) {
        return this.roleService.insert(mumberRole);
    }
}
