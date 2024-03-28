package com.zunke.shopmanager.Util;/**
 * @author ：EdgeYu
 * @date 2021-09-24 19:47
 * @version ：1.0
 */

import org.apache.commons.lang.RandomStringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName OrdersNo
 * @Date 2021/9/24 19:47
 * @Author 何永波
 * @Description TODO 随机生成用户订单号
 */
public class MyUtils {

    public String getOrderNum(Integer userId) {
        //时间（精确到毫秒）
        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String localDate = LocalDateTime.now().format(ofPattern);
        //3位随机数
        String randomNumeric = RandomStringUtils.randomNumeric(3);
        //5位用户id
        int subStrLength = 5;
        String sUserId = userId.toString();
        int length = sUserId.length();
        String str;
        if (length >= subStrLength) {
            str = sUserId.substring(length - subStrLength, length);
        } else {
            str = String.format("%0" + subStrLength + "d", userId);
        }
        String orderNum = localDate + randomNumeric + str;
        return orderNum;

    }
}
