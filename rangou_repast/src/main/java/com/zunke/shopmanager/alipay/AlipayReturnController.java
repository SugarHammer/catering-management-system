package com.zunke.shopmanager.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.zunke.shopmanager.config.AlipayConfig;
import com.zunke.shopmanager.service.impl.TradeVerificationServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author Yiming
 * @version 1.0
 * @since 2021/9/25 10:10
 */
@Controller
@Api(value = "支付宝同步验签管理",tags = "支付宝同步验签管理接口API")
public class AlipayReturnController {

    @Resource
    private TradeVerificationServiceImpl tradeVerificationServiceImpl;

    @RequestMapping("/return")
    public String returnUrlMethod(HttpServletRequest request) throws IOException, AlipayApiException, ServletException {
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if (signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

//            String cartId = new String(request.getParameter("body").getBytes("ISO-8859-1"), "UTF-8");
            // 这里应该保存数据到数据库
            System.out.println("return验签成功");
            if (tradeVerificationServiceImpl.tradeVerification(out_trade_no, trade_no)) {
                System.out.println("查询接口验签成功");
                System.out.println("前台日志打印------------------------");
                Map<String,String[]> map = request.getParameterMap();
                Set<String> mapset = map.keySet();
                for (String key : mapset){
                    System.out.println(key + " : "+ Arrays.toString(map.get(key)));
                }
                System.out.println("-----------------------------------");

//                return "trade_no:" + trade_no + "<br/>out_trade_no:" + out_trade_no + "<br/>total_amount:" + total_amount;
                return "redirect:success.html";
            }
            return "验签失败";
        } else {
            return "验签失败";
        }
    }
}
