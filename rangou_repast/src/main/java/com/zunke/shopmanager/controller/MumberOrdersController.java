package com.zunke.shopmanager.controller;/**
 * @author ：EdgeYu
 * @date 2021-09-23 14:59
 * @version ：1.0
 */

import com.zunke.shopmanager.service.impl.MumberOrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName mumberOrdersController
 * @Date 2021/9/23 14:59
 * @Author 何永波
 * @Description TODO
 */
@RestController
@RequestMapping("/MumberOrders")
@Api(value = "用户订单信息(MumberOrders)管理", tags = "用户订单信息(MumberOrders)管理接口API")
@ApiResponses({
        @ApiResponse(code = 400, message = "请求参数不完整或者错误"),
        @ApiResponse(code = 404, message = "找不到页面"),
        @ApiResponse(code = 405, message = "请求方式不正确，比如后台接收是post，但前端请求的是get"),
        @ApiResponse(code = 406, message = "页面地址和接口名冲突"),
        @ApiResponse(code = 415, message = "请求格式不正确，前端可能没有把请求头修改为json，也可能前端请求的json对象没有转换为字符串"),
        @ApiResponse(code = 500, message = "后台服务逻辑错误")
})
public class MumberOrdersController {
    @Resource
    private MumberOrdersService mumberOrdersService;

    @RequestMapping(value = "/selectByMumberId", method = RequestMethod.GET)
    @ApiOperation(value = "通过会员id查询一个会员订单信息(MumberOrders的接口", notes = "通过会员id查询一个会员订单信息(MumberOrders的接口", httpMethod = "GET")
    public Map<String,Object> selectByCartsId(Integer cartId){


        return mumberOrdersService.selectBycartsId(cartId);
    }
}
