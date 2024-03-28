package com.zunke.shopmanager.alipay;

import com.alipay.api.AlipayApiException;
import com.zunke.shopmanager.service.impl.AfterPayServiceImpl;
import com.zunke.shopmanager.service.impl.AlipayServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yiming
 * @version 1.0
 * @since 2021/9/26 11:34
 */
@RestController
@Api(value = "支付完成订单处理逻辑管理",tags = "支付完成订单处理逻辑管理接口API")
@ApiResponses({
        @ApiResponse(code=400,message="请求参数不完整或者错误"),
        @ApiResponse(code=404,message="找不到页面"),
        @ApiResponse(code=405,message="请求方式不正确，比如后台接收是post，但前端请求的是get"),
        @ApiResponse(code=406,message="页面地址和接口名冲突"),
        @ApiResponse(code=415,message="请求格式不正确，前端可能没有把请求头修改为json，也可能前端请求的json对象没有转换为字符串"),
        @ApiResponse(code=500,message="后台服务逻辑错误")
})
public class AfterPayController {

    @Resource
    private AfterPayServiceImpl afterPayServiceImpl;
    @RequestMapping("/afterPay")
    @ApiOperation(value = "支付完成订单处理接口",notes = "支付完成订单处理接口",httpMethod = "GET")
    protected void scanToPay(HttpServletRequest request, HttpServletResponse response,String cartId) throws ServletException, IOException, AlipayApiException {
        afterPayServiceImpl.AfterPay(request,response,cartId);
    }

}
