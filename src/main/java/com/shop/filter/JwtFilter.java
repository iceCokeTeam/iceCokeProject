package com.shop.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shop.token.JwtToken;
import com.shop.utils.HttpCode;
import com.shop.utils.Message;
import com.shop.utils.ServletUtils;
import lombok.SneakyThrows;
import org.apache.coyote.ErrorState;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends BasicHttpAuthenticationFilter {

    private final String TOKEN_HEADER_NAME = "authToken";

    /**
     * 执行登录认证
     *
     * @param request     ServletRequest
     * @param response    ServletResponse
     * @param mappedValue mappedValue
     * @return 是否成功
     */
    @SneakyThrows
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        String token = ((HttpServletRequest) request).getHeader(TOKEN_HEADER_NAME);
        if (token != null) {
            return executeLogin(request, response);
        }
        String url = ((HttpServletRequest) request).getRequestURL().toString();
        if (url.matches(".*admin.*")) {
            JSONObject json = new JSONObject();
            json.put("status", HttpCode.FORBIDDEN);
            json.put("msg", Message.AUTHENTICATION_FAILED);
            ServletUtils.renderString((HttpServletResponse) response, json.toJSONString());
            return false;
        }
        // 如果请求头不存在 Token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
        return true;
    }


    /**
     * 执行登录
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(TOKEN_HEADER_NAME);
        JwtToken jwtToken = new JwtToken(token);

        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        try {
            getSubject(request, response).login(jwtToken);
        } catch (IncorrectCredentialsException e) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", HttpCode.FORBIDDEN);
            jsonObject.put("msg", Message.AUTHENTICATION_FAILED);
            httpServletResponse.getWriter().print(jsonObject);
            return false;
        } catch (AuthenticationException e) {
            return false;
        }
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    // 跨域支持
//    @Override
//    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
//        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
//        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
//
//        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
//            httpServletResponse.setStatus(HttpStatus.OK.value());
//            return false;
//        }
//        return super.preHandle(request, response);
//    }
}
