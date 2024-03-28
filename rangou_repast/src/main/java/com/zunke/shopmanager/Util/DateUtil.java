package com.zunke.shopmanager.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间类工具
 * @author Boyou
 * @date
 * @version
 */
public class DateUtil {
    public static String dateToStringUtil(Date date){
            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd  HH:mm:ss");
            return sdf.format(date);
    }


}
