package com.shop.utils;

public class RegexUtil {

    public static boolean isDigital(String str) {
        if (str == null)
            return false;
        String pattern = "^[0-9]*$";
        return str.matches(pattern);
    }
}
