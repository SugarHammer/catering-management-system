package com.zunke.shopmanager.controller;

import com.zunke.shopmanager.pojo.MumberLevel;
import com.zunke.shopmanager.service.impl.MumberLevelServiceImpl;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 会员等级表(MumberLevel)表控制层
 *
 * @author hy
 * @version 1.0
 * @since 2021-09-15 08:44:34
 */

@RestController
@RequestMapping("/mumberLevel")
@Api(value = "会员等级表(MumberLevel)管理", tags = "会员等级表(MumberLevel)管理接口API")
@ApiResponses({
        @ApiResponse(code = 400, message = "请求参数不完整或者错误"),
        @ApiResponse(code = 404, message = "找不到页面"),
        @ApiResponse(code = 405, message = "请求方式不正确，比如后台接收是post，但前端请求的是get"),
        @ApiResponse(code = 406, message = "页面地址和接口名冲突"),
        @ApiResponse(code = 415, message = "请求格式不正确，前端可能没有把请求头修改为json，也可能前端请求的json对象没有转换为字符串"),
        @ApiResponse(code = 500, message = "后台服务逻辑错误")
})
public class MumberLevelController {
    /**
     * 服务对象
     */
    @Resource
    private MumberLevelServiceImpl mumberLevelServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param index 需要查询的页码
     * @param name  模糊查询的内容
     * @param limit 分页数量
     * @return 单条数据
     */
    @RequestMapping(value = "/selectForPage", method = RequestMethod.GET)
    @ApiOperation(value = "列表查询分页接口", notes = "列表查询分页接口", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "int", required = true, example = "1"),
            @ApiImplicitParam(name = "name", value = "会员等级表(MumberLevel)名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "分页数量", paramType = "query", dataType = "int", required = true, example = "10")
    })
    public Map<String, Object> selectForPage(@RequestParam(name = "page") int index, @RequestParam(name = "limit") int limit, String name) {
        return this.mumberLevelServiceImpl.selectForPage(index, limit, name);
    }

    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有数据接口", notes = "查询所有数据接口", httpMethod = "GET")
    public Map<String, Object> selectAll() {
        return this.mumberLevelServiceImpl.selectAll();
    }

    /**
     * 通过主键删除单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ApiOperation(value = "根据一个主键删除一条会员等级表(MumberLevel记录的接口", notes = "根据一个主键删除一条会员等级表(MumberLevel记录的接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", paramType = "query", dataType = "String", required = true),
    })
    public Map<String, Object> deleteById(@RequestParam("id") String id) {
        return this.mumberLevelServiceImpl.deleteById(id);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @RequestMapping(value = "/selectById", method = RequestMethod.GET)
    @ApiOperation(value = "通过主键查询一个会员等级表(MumberLevel的接口", notes = "通过主键查询一个会员等级表(MumberLevel的接口", httpMethod = "GET")
    public Map<String, Object> selectById(Integer id) {
        return this.mumberLevelServiceImpl.selectById(id);
    }

    /**
     * 通过主键更新单条数据
     *
     * @param mumberLevel 一个数据库对应的POJO数据对象
     * @return 单条数据
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    @ApiOperation(value = "通过主键更新一个会员等级表(MumberLevel的接口", notes = "通过主键更新一个会员等级表(MumberLevel的接口", httpMethod = "POST")
    public Map<String, Object> updateById(@RequestBody @ApiParam(name = "mumberLevel", value = "pojo模型", required = true) MumberLevel mumberLevel) {
        return this.mumberLevelServiceImpl.updateById(mumberLevel);
    }

    /**
     * 通过一个pojo对象新增单条数据
     *
     * @param mumberLevel 一个数据库对应的POJO数据对象
     * @return 返回插入的主键id
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "根据完整记录新增一个会员等级表(MumberLevel的接口", notes = "根据完整记录新增一个会员等级表(MumberLevel的接口", httpMethod = "POST")
    public Map<String, Object> insert(@RequestBody @ApiParam(name = "mumberLevel", value = "pojo模型", required = true) MumberLevel mumberLevel) {
        return this.mumberLevelServiceImpl.insert(mumberLevel);
    }

}
