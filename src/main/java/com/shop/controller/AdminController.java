package com.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.shop.pojo.Admin;
import com.shop.service.AdminService;
import com.shop.utils.HttpCode;
import com.shop.utils.Message;
import com.shop.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    @RequestMapping("/register")
    public Result registerAdmin(Admin admin) {
        if (admin.getAdminName() == null && admin.getPassword() == null && admin.getEmail() == null && admin.getName() == null && admin.getAdminImg() == null) {
            return Result.create(HttpCode.BAD_REQUEST, Message.PARAMETER_ERROR);
        }

        if (adminService.registerAdmin(admin) == 1) {
        } else {
            return Result.create(HttpCode.OK, Message.REGISTER_SUCCESS);
        }
        return Result.create(HttpCode.INTERNAL_SERVER_ERROR, Message.REGISTER_ERROR);
    }

    @RequestMapping("/login")
    public Map<String, Object> loginAdmin(String adminName, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(adminName, password);
        Map<String, Object> map = new HashMap<>();
        try {
            subject.login(token);
            map.put("status", HttpCode.OK);
            map.put("msg", "登录成功");
        } catch (UnknownAccountException e) {
            map.put("status", HttpCode.FORBIDDEN);
            map.put("msg", "用户名不存在");
        } catch (IncorrectCredentialsException e) {
            map.put("status", HttpCode.FORBIDDEN);
            map.put("msg", "密码错误");
        } catch (AuthenticationException e) {
            map.put("status", HttpCode.FORBIDDEN);
            map.put("msg", "认证失败");
        }
        return map;
    }
}
