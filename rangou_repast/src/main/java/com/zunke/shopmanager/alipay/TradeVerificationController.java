package com.zunke.shopmanager.alipay;

import com.alipay.api.AlipayApiException;
import com.zunke.shopmanager.service.impl.TradeVerificationServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

/**
 * @author Yiming
 * @version 1.0
 * @since 2021/9/23 18:48
 */
@RestController
@RequestMapping("/verification")
@Api(value = "支付宝支付查询接口验证管理",tags = "支付宝支付查询接口验证管理接口API")
@ApiResponses({
        @ApiResponse(code=400,message="请求参数不完整或者错误"),
        @ApiResponse(code=404,message="找不到页面"),
        @ApiResponse(code=405,message="请求方式不正确，比如后台接收是post，但前端请求的是get"),
        @ApiResponse(code=406,message="页面地址和接口名冲突"),
        @ApiResponse(code=415,message="请求格式不正确，前端可能没有把请求头修改为json，也可能前端请求的json对象没有转换为字符串"),
        @ApiResponse(code=500,message="后台服务逻辑错误")
})
public class TradeVerificationController {
    @Resource
    private TradeVerificationServiceImpl tradeVerificationServiceImpl;

    @RequestMapping("/tradeVerification")
    @ApiOperation(value = "订单接口验证接口",notes = "订单接口验证接口",httpMethod = "GET")
    protected boolean tradeVerification(String out_trade_no, String trade_no) throws ServletException, IOException, AlipayApiException {
        return tradeVerificationServiceImpl.tradeVerification(out_trade_no,trade_no);
    }
}