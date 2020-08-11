package com.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.shop.pojo.Admin;
import com.shop.service.AdminService;
import com.shop.token.CustomizedToken;
import com.shop.utils.HttpCode;
import com.shop.utils.Message;
import com.shop.utils.Result;
import com.shop.utils.TokenUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
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
        if (adminService.registerAdmin(admin) == 1) {
            return Result.create(HttpCode.OK, Message.REGISTER_SUCCESS);
        }
        return Result.create(HttpCode.INTERNAL_SERVER_ERROR, Message.REGISTER_ERROR);
    }

    @PostMapping("/login")
    public Result loginAdmin(String adminName, String password) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        CustomizedToken token = new CustomizedToken(adminName, password, "admin");
        JSONObject json = new JSONObject();
        try {
            subject.login(token);
            Admin realAdmin = adminService.selectAdminByName(adminName);
            json.put("admin", realAdmin);
            json.put("authToken", TokenUtil.createToken(realAdmin.getId().toString()));
            return Result.create(HttpCode.OK, Message.LOGIN_SUCCESS, json);
        } catch (UnknownAccountException e) {
            return Result.create(HttpCode.BAD_REQUEST, Message.USER_NOT_EXISTS);
        } catch (IncorrectCredentialsException e) {
            return Result.create(HttpCode.BAD_REQUEST, Message.PASSWORD_ERROR);
        } catch (AuthenticationException e) {
            return Result.create(HttpCode.BAD_REQUEST, Message.AUTHENTICATION_FAILED);
        }
    }

    @PostMapping("/update")
    public Result updateAdmin(Admin admin) {
        if (adminService.updateAdmin(admin) == 1) {
            return Result.create(HttpCode.OK, Message.UPDATE_SUCCESS);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.UPDATE_FAILED);
    }

    @PostMapping("/del")
    public Result delAdmin(String id) {
        if (adminService.deleteAdmin(id) == 1) {
            return Result.create(HttpCode.OK, Message.DEL_SUCCESS);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.DEL_FAILED);
    }

}
