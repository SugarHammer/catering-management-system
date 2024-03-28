package com.zunke.shopmanager.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.zunke.shopmanager.config.AlipayConfig;
import com.zunke.shopmanager.mapper.CopyInfoMapper;
import com.zunke.shopmanager.mapper.UpdateDiningActionMapper;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yiming
 * @version 1.0
 * @since 2021/9/23 18:49
 */
@Service
public class TradeVerificationServiceImpl {

    @Resource
    private CopyInfoMapper copyInfoMapper;

    @Resource
    private UpdateDiningActionMapper updateDiningActionMapper;
    /*
      由于同步返回的不可靠性，支付结果必须以异步通知或查询接口返回为准，不能依赖同步跳转。
      商户系统接收到异步通知以后，必须通过验签（验证通知中的sign参数）来确保支付通知是由支付宝发送的。
    */


    /**
     * @Author Yiming
     * @Description //验证当前订单的状态
     * @Date 14:18 2021/9/24
     * @Param [out_trade_no, trade_no, cartId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/

    public boolean tradeVerification(String out_trade_no, String trade_no) throws ServletException, IOException, AlipayApiException {
        {
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,AlipayConfig.app_id , AlipayConfig.merchant_private_key, "json", "UTF-8", AlipayConfig.alipay_public_key, "RSA2");
            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no", out_trade_no);
            bizContent.put("trade_no", trade_no);
            request.setBizContent(bizContent.toString());
            AlipayTradeQueryResponse response = alipayClient.execute(request);
//            System.out.println(response.getBody());

            Map<String, Object> map = new HashMap<>();
            return response.isSuccess();
//            if (!response.isSuccess()) {
//                map.put("code",500);
//                map.put("msg","调用失败");
//                return map;
//            }else if ("TRADE_SUCCESS".equals(response.getTradeStatus())){
//                // 复制购物车信息表（cart_dishes_combo）中的套菜信息和单品信息到combo表和dishes表
//                copyInfoMapper.copyCartsInfo(cartId);
//                // 复制购物车信息表到订单表
//
//                // 修改餐桌action为非占用
//                updateDiningActionMapper.UpdateDiningActionByDiningId(updateDiningActionMapper.SelectDiningIdByCartId(cartId));
//                map.put("code",200);
//                map.put("msg","支付成功");
//                map.put("data",response.getBody());
//                return map;
//            }
//            map.put("code",500);
//            map.put("msg","支付失败");
//            return map;

        }
    }
}
