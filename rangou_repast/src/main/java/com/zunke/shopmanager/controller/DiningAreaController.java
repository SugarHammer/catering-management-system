package com.zunke.shopmanager.controller;

import com.zunke.shopmanager.pojo.DiningArea;
import com.zunke.shopmanager.service.impl.DiningAreaServiceImpl;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 餐桌区域表(DiningArea)表控制层
 *
 * @author ZhangHongNeng
 * @since 2021-09-13 11:00:52
 * @version 1.0
 */
@RestController
@RequestMapping("/diningArea")
@Api(value = "餐桌区域表(DiningArea)管理",tags = "餐桌区域表(DiningArea)管理接口API")
@ApiResponses({
        @ApiResponse(code=400,message="请求参数不完整或者错误"),
        @ApiResponse(code=404,message="找不到页面"),
        @ApiResponse(code=405,message="请求方式不正确，比如后台接收是post，但前端请求的是get"),
        @ApiResponse(code=406,message="页面地址和接口名冲突"),
        @ApiResponse(code=415,message="请求格式不正确，前端可能没有把请求头修改为json，也可能前端请求的json对象没有转换为字符串"),
        @ApiResponse(code=500,message="后台服务逻辑错误")
})
public class DiningAreaController {
    /**
     * 服务对象
     */
    @Resource
    private DiningAreaServiceImpl diningAreaServiceImpl;

    /**
     * 通过主键查询单条数据
     * @param index     需要查询的页码
     * @param name      模糊查询的内容
     * @return          单条数据
     */
    @RequestMapping(value = "/selectForPage", method = RequestMethod.GET)
    @ApiOperation(value = "列表查询分页接口",notes = "列表查询分页接口",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页码",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "name",value = "**名称",paramType = "query",dataType = "String")
    })
    public Map<String, Object> selectForPage(@RequestParam(name = "page") int index, String name) {
        return this.diningAreaServiceImpl.selectForPage(index, name);
    }

    /**
     *
     * @param diningAreaName    区域名称
     * @param diningAreaCoding  区域编码
     * @return  返回多条数据
     */
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有和区域查询数据接口",notes = "查询所有和区域查询数据接口",httpMethod = "GET")
    public Map<String, Object> selectAll(String diningAreaName, String diningAreaCoding) {
        return this.diningAreaServiceImpl.selectAll(diningAreaName,diningAreaCoding);
    }
    
    /**
     * 通过餐桌区域主键删除数据
     *
     * @param id    餐桌区域主键
     * @return      单条数据
     */
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ApiOperation(value = "根据餐桌区域主键删除该条记录的接口",notes = "根据餐桌区域主键删除该条记录的接口",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "主键",paramType = "query",dataType = "String",required = true),
    })
    public Map<String, Object> deleteById(@RequestParam("id") String id) {
        return this.diningAreaServiceImpl.deleteById(id);
    }
    
    /**
     * 通过区域主键查询数据
     *
     * @param id    区域主键
     * @return      实例对象
     */
    @RequestMapping(value = "/selectById", method = RequestMethod.GET)
    @ApiOperation(value = "通过主键查询一个接口",notes = "通过主键查询一个接口",httpMethod = "GET")
    public Map<String, Object> selectById(Integer id) {
        return this.diningAreaServiceImpl.selectById(id);
    }
    
    /**
     * 通过主键更新单条数据
     *
     * @param diningArea    一个数据库对应的POJO数据对象
     * @return              是否成功
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    @ApiOperation(value = "通过主键更新一个接口",notes = "通过主键更新一个接口",httpMethod = "POST")
    public Map<String, Object> updateById(@RequestBody @ApiParam(name = "diningArea",value = "pojo模型",required = true) DiningArea diningArea) {
        return this.diningAreaServiceImpl.updateById(diningArea);
    }
    
    /**
     * 通过一个pojo对象新增单条数据
     *
     * @param diningArea    一个数据库对应的POJO数据对象
     * @return              返回是否成功
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "根据完整记录新增一个接口",notes = "根据完整记录新增一个接口",httpMethod = "POST")
    public Map<String, Object> insert(@RequestBody @ApiParam(name = "diningArea",value = "pojo模型",required = true) DiningArea diningArea) {
        System.out.println(diningArea);
        return this.diningAreaServiceImpl.insert(diningArea);
    }

    /**
     * 按区域查询所有餐桌
     * @param diningAreaName    区域名称
     * @return                  返回多条数据
     */
    @RequestMapping(value = "/selectByArea", method = RequestMethod.GET)
    @ApiOperation(value = "通过区域查询的接口",notes = "通过区域查询的接口",httpMethod = "GET")
    public Map<String, Object> selectByArea(String  diningAreaName) {
        return this.diningAreaServiceImpl.selectByArea(diningAreaName);
    }


}