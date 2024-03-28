package com.zunke.shopmanager.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.zunke.shopmanager.config.AlipayConfig;
import com.zunke.shopmanager.mapper.AlipaySubsidiaryMapper;
import com.zunke.shopmanager.mapper.CartMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yiming
 * @version 1.0
 * @since 2021/9/22 16:10
 */
@Service
public class AlipayServiceImpl {

    @Resource
    private AlipaySubsidiaryMapper alipaySubsidiaryMapper;
    /**
     * @Author Yiming
     * @Description //扫码支付
     * @Date 11:03 2021/9/23
     * @Param [request, response]
     * @return void
     **/
    //http://localhost:8080/pay/scanToPay
    public void scanToPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AlipayApiException {

        String json = request.getReader().readLine();
        System.out.println("json=" + json);
        String serverURL = "https://openapi.alipaydev.com/gateway.do";     // alipay测试支付地址   生产环境支付地址为https://openapi.alipay.com/gateway.do
        String APP_ID = "2021000118615328";   // 创建的应用ID
        // 自己APP的私钥
        String mKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC9e1Qj1XApIk4qaV4hL9DMIAXjjzoAjGPEyv50+xR5VI8dYjWWzFftkKrjV6v3ed3GRSK3SrHPCvbKx53jTcQUVdjWHCdlivcriM2QS47ZT0T6nFPdnAsPX/Hni5R++l2HG6XwbL9ZpXo/gHu6UpUki8gq/fYCmHab8zWOsikJqYbB9LyrgBb/myOhhHlv8bxCmR8rAcwX1rFLZ7grffqtLzF3vHHsvg1f8oD3c2FfsqO+Lv5GWDaEDAfBYSNIhvHTB/BZAZx13k48yevkarqqRW7EhZ8lbsAasFTxdxnQq70Lxxiy5MVoLTSaXR5VY2v2Cr180Bn2mqiLbZlSTeMVAgMBAAECggEAVesXUwxUSIKiuP6ATUIWRoj6wp3OajM+hhyZ10eGmCGsp2FB9XgBItrnciDt/FCJLH9q53v6Nmy9TWPIhypbD/GBWkz33EZwH5Bvzc9FHeqg15aNPL6mETmrQhf7pLw4LqxtySA2gBrV3PeQowMRV7n+cOgpZjHmDn7wuRy2GQe7IQbZus4jKG0gK+Eo6yy0ygtDSDpje/+UOm35tmJO+pvMbNJTU/r6UCQ9ikCV0P9oJkffJzlinWif9RXqixq2r5pRCZTtR3acykn+u6iVfGib6ri8K4tspPFdTlat51pFa64ICBWSQgw3CFxxdakz4gY6q2h4bEoUNOB4HJpK4QKBgQDiPyuDKOtu8Z1Rg/OGfDC8D8WIRFfIVkC8v7+Cv/M2M5AYTJ/Pit/rKPvgAfMc5mZPhS2jvDH/gxFIRpWO2WGhy4tXc+s/9fuXU3PVU41gVYijlyS+GgPjcH1YH6e6iIuBtthjKAPoGErlYACcljL8kjwmKweF1RF5VcQi242SiQKBgQDWZm1M5VkjdmgV1bWApGZDdqYXzAoLSDJpYGssS0wlFHQcdmNrTnk+3aat7abt3/cLD8Rby+rGiI3QL5UPrKbaJPUMtdzIVW8Nf2VTwiMueWIWd0twMxdVTO3IzG9GNoZXsQKPRoQviqp8d4VZpR2f8JX2G+gXoFRu1mDx9k3ZLQKBgEMsQj0E7zHXZsAHD0zEnquXeyyELhP7F+lyfOfTGeDj2kp8pY43S1hEqNPPgGD6PzIo9m4AuE+GmKD+4/Svsf8ZsdtbPWOWIwbA7klOL1GE07bOb0hJ7RGgGncJnwEQ/Wqb4Q8/mDlWzRXhNQ1IwwaeXGFwsSEZK8D8p+k5ioURAoGBAKFAmlZqbPeSF8jMWl2tyEhXVaayTJ+dpmTBIvpGi7ebwZSiqjXL6L8SoElSY+mnODw2mJ3eKe82R0rcbsVCOp/5ieEMa0i1kPfa6E/txN4wZ0i0YqW5l4QNfw42TD7HxZTwyhcOl17WcVF55268FCL2JSPca1xRtw9B59o0QOrlAoGBAK1Dtrc3G37Eb8p2fJXAQl8eOOB7yYas8as7ADBOtGP0zuDFbve2KONoL0Cp1Ori8M+jtQggf5VpkXjShtdY6gZrU66GotxSqUEtfQRzO7Dsxgk3WD93CqbjCaEUjmhprVcbVfTQs9/o/hxEZi+raTDDyx9ba8KdaOdg1DsvVY2z";
        // 支付宝的公钥(使用自己APP的公钥兑换)
        String aKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsGFGwtP9EaC8m8hCRbrq93oVo1RDqDjbmOHVnGAOcuDKdyWPXnV85GRBebrdt5kOSQ17B06Jevfdgawtg7dqJNOh0UBpEDjJYTItxNo6dzMaowCkJ3J95aeBRMFQxuty6Xlz5Cd6GXFxJViYWc0bCBeTbwqCi1vmVPrenqvpIbaQL5miEhKb5WPfHzwpty1v6kryCn7C9Wn91Lh6svebp4cNCinFh0JxCgWT6i8f/UXGN8wEBpcYMNdWXAy2aAu2obkU7QhxkqO70ocj6sMTH8RF7/QW88OH5U4/j7jqhd2ihwJWFrgAC69yF+cgT3vo2IzklYiOdCYCrLFzRo5aOwIDAQAB";
        AlipayClient alipayClient = new DefaultAlipayClient(serverURL, APP_ID, mKey, "json", "utf-8", aKey, "RSA2"); //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();    // 创建API对应的request
        alipayRequest.setReturnUrl("http://localhost:8080/success.html");          // 当调用接口成功，返回的页面
        alipayRequest.setNotifyUrl("http://localhost:8080/payReturnServlet");   // 在公共参数中设置回跳和通知地址(必须在公网IP)

        /*
             填充业务参数
                 out_trade_no      每笔订单号需要自身的唯一id，支付宝会根据此id判断是否交易
                 product_code
                 total_amount      这笔订单的价格
                 subject           订单商品名称(也可以为标题)
                 body              订单描述
                 passback_params   回传参数
                 extend_params
                 product_code   支付方式：QUICK_WAP_PAY 登录账号支付
         */
        // 接收订单号 例如："20190210010118666"
//        String out_trade_no = request.getParameter("dd"+System.currentTimeMillis());          // 每笔订单号需要自身的唯一id，支付宝会根据此id判断是否交易
        String out_trade_no = "dd"+System.currentTimeMillis();
        String product_code = "FAST_INSTANT_TRADE_PAY";                      // 支付方式：FAST_INSTANT_TRADE_PAY扫码支付
        String total_amount = "20000";                                    // 这笔订单的价格
        String subject = "成都尊科u8餐饮";                             // 订单商品名称
        String body = "【淘宝爆款】zzz";                                // 订单商品描述

        String bizContent = "{" +
                "\"out_trade_no\":\"" + out_trade_no + "\"," +
                "\"product_code\":\"" + product_code + "\"," +
                "\"total_amount\":" + total_amount + "," +
                "\"subject\":\"" + subject + "\"," +
                "\"body\":\"" + body + "\"}";                                // 生成支付json数据
        System.out.println("支付生成json数据 bizContent=" + bizContent);      // 打印json支付数据

        alipayRequest.setBizContent(bizContent);                             // 提交支付请求

        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody();        //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }
    /**
     * @Author Yiming
     * @Description //线上支付（购物车点单支付）
     * @Date 10:50 2021/9/23
     * @Param [request, response, totalAmount]
     * @return void
     **/
    // http://localhost:8080/pay/payOnline?totalAmount=
    public void payOnline(HttpServletRequest request, HttpServletResponse response, String cartId) throws ServletException, IOException, AlipayApiException {

        String out_trade_no = "DX" + System.currentTimeMillis();   // 订单号一旦支付成功，永久在此应用中不可使用
        String total_amount = "" + alipaySubsidiaryMapper.selectPriceBycartId(cartId);                          // 这笔订单的价格
        String subject = "成都尊科u8餐饮";                            // 订单商品名称
        String body = "" + cartId;

        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", "UTF-8", AlipayConfig.alipay_public_key, "RSA2");      //  获得初始化的AlipayClient
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();   //  创建API对应的request
        alipayRequest.setReturnUrl(AlipayConfig.return_url);          // 用户确认支付后，支付宝get请求returnUrl
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);      // 交易成功后，支付宝post请求notifyUrl（商户入参传入）

        String product_code = "QUICK_WAP_PAY";                     // 支付方式：QUICK_WAP_PAY 登录账号支付

        String bizContent = "{" +
                "\"out_trade_no\":\"" + out_trade_no + "\"," +
                "\"product_code\":\"" + product_code + "\"," +
                "\"total_amount\":" + total_amount + "," +
                "\"subject\":\"" + subject + "\"," +
                "\"body\":\"" + body + "\"}";
        System.out.println("支付生成json数据 bizContent=" + bizContent);

        alipayRequest.setBizContent(bizContent);// 提交支付请求

        String form = alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(form);                                // 直接将完整的表单html输出到页/面
        response.getWriter().flush();
        response.getWriter().close();

    }

    // http://eska9b.natappfree.cc/pay/payOnline?cartId=

}
