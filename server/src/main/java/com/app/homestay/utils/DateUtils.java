package com.app.homestay.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DateUtils {

    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d = new Date();
        String dateNowStr = sdf.format(d);
        return dateNowStr;
    }

    /**
     * java生成随机数字10位数
     */
    public static String getRandom(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }

}
