package com.zunke.shopmanager.controller;

import com.zunke.shopmanager.service.impl.ReportsSoldDishesServiceImpl;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Yiming
 * @version 1.0
 * @since 2021/9/16 8:59
 */
@RestController
@RequestMapping("/reports")
@Api(value = "菜品销售情况管理",tags = "菜品销售情况管理接口API")
@ApiResponses({
        @ApiResponse(code=400,message="请求参数不完整或者错误"),
        @ApiResponse(code=404,message="找不到页面"),
        @ApiResponse(code=405,message="请求方式不正确，比如后台接收是post，但前端请求的是get"),
        @ApiResponse(code=406,message="页面地址和接口名冲突"),
        @ApiResponse(code=415,message="请求格式不正确，前端可能没有把请求头修改为json，也可能前端请求的json对象没有转换为字符串"),
        @ApiResponse(code=500,message="后台服务逻辑错误")
})
public class ReportsSoldDishesController {
    @Resource
    private ReportsSoldDishesServiceImpl reportsSoldDishesServiceImpl;

    @RequestMapping(value = "/selectForDishesSold", method = RequestMethod.GET)
    @ApiOperation(value = "菜品销售情况查询接口",notes = "菜品销售情况查询接口",httpMethod = "GET")
    public Map<String,Object> selectForDishesSold(@RequestParam(name = "page") int index ,String dishesName){
        return reportsSoldDishesServiceImpl.selectForDishesSold(index,dishesName);
    }
}
