package com.shop.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    public static String currentTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
