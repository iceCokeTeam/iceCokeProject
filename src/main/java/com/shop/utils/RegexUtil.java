package com.shop.utils;

public class RegexUtil {

    public static boolean isDigital(String str) {
        String pattern = "^[0-9]*$";
        return str.matches(pattern);
    }
}
