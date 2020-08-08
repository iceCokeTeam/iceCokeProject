package com.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.shop.pojo.Admin;
import com.shop.service.AdminService;
import com.shop.utils.CustomizedToken;
import com.shop.utils.HttpCode;
import com.shop.utils.Message;
import com.shop.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    @PostMapping("/register")
    public Result registerAdmin(Admin admin) {
        if (admin.getAdminName() == null && admin.getPassword() == null && admin.getEmail() == null && admin.getName() == null && admin.getAdminImg() == null) {
            return Result.create(HttpCode.BAD_REQUEST, Message.PARAMETER_ERROR);
        }
        if (adminService.selectAdminByName(admin.getAdminName()) != null) {
            return Result.create(HttpCode.OK, Message.USER_EXISTS);
        }
        if (adminService.registerAdmin(admin) == 1) {
            return Result.create(HttpCode.OK, Message.REGISTER_SUCCESS);
        }
        return Result.create(HttpCode.INTERNAL_SERVER_ERROR, Message.REGISTER_ERROR);
    }

    @PostMapping("/login")
    public Result loginAdmin(String adminName, String password) {
        Subject subject = SecurityUtils.getSubject();
        CustomizedToken token = new CustomizedToken(adminName, password, "admin");
        JSONObject json = new JSONObject();
        try {
            subject.login(token);
            json.put("admin", adminService.selectAdminByName(adminName));
            return Result.create(HttpCode.OK, Message.LOGIN_SUCCESS, json);
        } catch (UnknownAccountException e) {
            return Result.create(HttpCode.BAD_REQUEST, Message.USER_NOT_EXISTS);
        } catch (IncorrectCredentialsException e) {
            return Result.create(HttpCode.BAD_REQUEST, Message.PASSWORD_ERROR);
        } catch (AuthenticationException e) {
            return Result.create(HttpCode.BAD_REQUEST, Message.AUTHENTICATION_FAILED);
        }
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}
