package com.zunke.shopmanager.alipay;

import com.alipay.api.AlipayApiException;
import com.zunke.shopmanager.service.impl.AlipayServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Yiming
 * @Description //支付接口
 * @Date 16:45 2021/9/22
 * @Param
 * @return
 **/
@Controller
@RequestMapping("/pay")
@Api(value = "支付宝支付管理",tags = "支付宝支付管理接口API")
@ApiResponses({
        @ApiResponse(code=400,message="请求参数不完整或者错误"),
        @ApiResponse(code=404,message="找不到页面"),
        @ApiResponse(code=405,message="请求方式不正确，比如后台接收是post，但前端请求的是get"),
        @ApiResponse(code=406,message="页面地址和接口名冲突"),
        @ApiResponse(code=415,message="请求格式不正确，前端可能没有把请求头修改为json，也可能前端请求的json对象没有转换为字符串"),
        @ApiResponse(code=500,message="后台服务逻辑错误")
})
public class AlipayController {
    @Resource
    private AlipayServiceImpl alipayServiceImpl;

    @RequestMapping("/scanToPay")
    @ApiOperation(value = "扫码支付接口",notes = "扫码支付接口",httpMethod = "GET")
    protected void scanToPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AlipayApiException {
        alipayServiceImpl.scanToPay(request,response);
    }

    @RequestMapping("/payOnline")
    @ResponseBody
    @ApiOperation(value = "账号支付接口",notes = "账号支付接口",httpMethod = "GET")
    protected void payOnline(HttpServletRequest request, HttpServletResponse response, String cartId) throws ServletException, IOException, AlipayApiException {
        alipayServiceImpl.payOnline(request,response,cartId);
    }
}
