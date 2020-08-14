package com.shop.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class ServletUtils {


    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }


    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }


    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    public static String renderString(HttpServletResponse response, String string) {
        response.setStatus(HttpStatus.OK.value());
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=UTF-8");
        try( PrintWriter writer = response.getWriter()) {
            writer.print(string);
        }catch (IOException e){
            return null;
        }
        return null;
    }

}