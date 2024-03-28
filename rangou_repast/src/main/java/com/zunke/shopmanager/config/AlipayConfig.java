package com.zunke.shopmanager.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2021-09-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	


	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2021000118615328";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC9e1Qj1XApIk4qaV4hL9DMIAXjjzoAjGPEyv50+xR5VI8dYjWWzFftkKrjV6v3ed3GRSK3SrHPCvbKx53jTcQUVdjWHCdlivcriM2QS47ZT0T6nFPdnAsPX/Hni5R++l2HG6XwbL9ZpXo/gHu6UpUki8gq/fYCmHab8zWOsikJqYbB9LyrgBb/myOhhHlv8bxCmR8rAcwX1rFLZ7grffqtLzF3vHHsvg1f8oD3c2FfsqO+Lv5GWDaEDAfBYSNIhvHTB/BZAZx13k48yevkarqqRW7EhZ8lbsAasFTxdxnQq70Lxxiy5MVoLTSaXR5VY2v2Cr180Bn2mqiLbZlSTeMVAgMBAAECggEAVesXUwxUSIKiuP6ATUIWRoj6wp3OajM+hhyZ10eGmCGsp2FB9XgBItrnciDt/FCJLH9q53v6Nmy9TWPIhypbD/GBWkz33EZwH5Bvzc9FHeqg15aNPL6mETmrQhf7pLw4LqxtySA2gBrV3PeQowMRV7n+cOgpZjHmDn7wuRy2GQe7IQbZus4jKG0gK+Eo6yy0ygtDSDpje/+UOm35tmJO+pvMbNJTU/r6UCQ9ikCV0P9oJkffJzlinWif9RXqixq2r5pRCZTtR3acykn+u6iVfGib6ri8K4tspPFdTlat51pFa64ICBWSQgw3CFxxdakz4gY6q2h4bEoUNOB4HJpK4QKBgQDiPyuDKOtu8Z1Rg/OGfDC8D8WIRFfIVkC8v7+Cv/M2M5AYTJ/Pit/rKPvgAfMc5mZPhS2jvDH/gxFIRpWO2WGhy4tXc+s/9fuXU3PVU41gVYijlyS+GgPjcH1YH6e6iIuBtthjKAPoGErlYACcljL8kjwmKweF1RF5VcQi242SiQKBgQDWZm1M5VkjdmgV1bWApGZDdqYXzAoLSDJpYGssS0wlFHQcdmNrTnk+3aat7abt3/cLD8Rby+rGiI3QL5UPrKbaJPUMtdzIVW8Nf2VTwiMueWIWd0twMxdVTO3IzG9GNoZXsQKPRoQviqp8d4VZpR2f8JX2G+gXoFRu1mDx9k3ZLQKBgEMsQj0E7zHXZsAHD0zEnquXeyyELhP7F+lyfOfTGeDj2kp8pY43S1hEqNPPgGD6PzIo9m4AuE+GmKD+4/Svsf8ZsdtbPWOWIwbA7klOL1GE07bOb0hJ7RGgGncJnwEQ/Wqb4Q8/mDlWzRXhNQ1IwwaeXGFwsSEZK8D8p+k5ioURAoGBAKFAmlZqbPeSF8jMWl2tyEhXVaayTJ+dpmTBIvpGi7ebwZSiqjXL6L8SoElSY+mnODw2mJ3eKe82R0rcbsVCOp/5ieEMa0i1kPfa6E/txN4wZ0i0YqW5l4QNfw42TD7HxZTwyhcOl17WcVF55268FCL2JSPca1xRtw9B59o0QOrlAoGBAK1Dtrc3G37Eb8p2fJXAQl8eOOB7yYas8as7ADBOtGP0zuDFbve2KONoL0Cp1Ori8M+jtQggf5VpkXjShtdY6gZrU66GotxSqUEtfQRzO7Dsxgk3WD93CqbjCaEUjmhprVcbVfTQs9/o/hxEZi+raTDDyx9ba8KdaOdg1DsvVY2z";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsGFGwtP9EaC8m8hCRbrq93oVo1RDqDjbmOHVnGAOcuDKdyWPXnV85GRBebrdt5kOSQ17B06Jevfdgawtg7dqJNOh0UBpEDjJYTItxNo6dzMaowCkJ3J95aeBRMFQxuty6Xlz5Cd6GXFxJViYWc0bCBeTbwqCi1vmVPrenqvpIbaQL5miEhKb5WPfHzwpty1v6kryCn7C9Wn91Lh6svebp4cNCinFh0JxCgWT6i8f/UXGN8wEBpcYMNdWXAy2aAu2obkU7QhxkqO70ocj6sMTH8RF7/QW88OH5U4/j7jqhd2ihwJWFrgAC69yF+cgT3vo2IzklYiOdCYCrLFzRo5aOwIDAQAB";
//    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlJJNS95LsSCAWdvF1NYYDzdxamu1aKIWoAz3iuqoS+7mek5WtwyVncPWPXDwb9vy7dNlqJJzGf2i32/2HDObWK7QIYrLSatu7KIaZJLsORFgJra/FVLyXiXoTvDy7RK2Kr8j1hgcyJUUEIOszlzgdTkPDgoNtMubEgc8GXbrXwznHaeVt7XDyJGtgPPeTuFMsxnSrr6ul8z0brHwogSsOgKr4m14yte3FmFzyaqeEEt/ylWnNE1EohsHw1p6b7W59yaV2qYruaoLKqcQRzM83flOYOOqThwdhI348NiIefFN9UsEi50Zw4h4MsdDE86b0AY6r/ynx2Kq2VRHZfDNJwIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/notify";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/return";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";



    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

