package com.shop.utils;

public class HttpCode {
    // 请求成功
    public static final Integer OK = 200;
    //
    public static final Integer BAD_REQUEST = 400;
    // 请求要求用户的身份认证
    public static final Integer UNAUTHORIZED = 401;
    // 服务器理解请求客户端的请求，但是拒绝执行此请求
    public static final Integer FORBIDDEN = 403;
    // 服务器内部错误，无法完成请求
    public static final Integer INTERNAL_SERVER_ERROR = 500;
    // 服务器不支持请求的功能，无法完成请求
    public static final Integer NOT_IMPLEMENTED = 501;
}
