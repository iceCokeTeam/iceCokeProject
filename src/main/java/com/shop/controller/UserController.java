package com.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.shop.dto.UserDTO;
import com.shop.mapper.UserMapper;
import com.shop.pojo.User;
import com.shop.service.UserService;
import com.shop.token.CustomizedToken;
import com.shop.utils.HttpCode;
import com.shop.utils.Message;
import com.shop.utils.Result;
import com.shop.utils.TokenUtil;
import org.apache.ibatis.annotations.Update;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Result registerUser(User user) {
        if (userService.registerUser(user) == 1) {
            return Result.create(HttpCode.OK, Message.REGISTER_SUCCESS);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.REGISTER_ERROR);
    }

    @PostMapping("/list")
    public Result userList(UserDTO userDTO) {
        List<User> users = userService.selectUserList(userDTO);
        if (users == null)
            return Result.create(HttpCode.BAD_REQUEST, Message.SELECT_FAILED);
        JSONObject json = new JSONObject();
        json.put("data", users);
        return Result.create(HttpCode.OK, Message.SELECT_SUCCESS, json);
    }

    @PostMapping("/id")
    public Result userById(String id) {
        User user = userService.selectUserById(id);
        if (user == null)
            return Result.create(HttpCode.BAD_REQUEST, Message.SELECT_FAILED);
        return Result.create(HttpCode.OK, Message.SELECT_SUCCESS);
    }

    @PostMapping("/update")
    public Result updateUser(User user) {
        if (userService.updateUser(user) == 1)
            return Result.create(HttpCode.OK, Message.UPDATE_SUCCESS);
        return Result.create(HttpCode.BAD_REQUEST, Message.UPDATE_FAILED);
    }

    @PostMapping("/del")
    public Result delUpdate(String id) {
        if (userService.deleteUser(id) == 1) {
            return Result.create(HttpCode.OK, Message.DEL_SUCCESS);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.DEL_FAILED);
    }

    @PostMapping("/login")
    public Result userLogin(User user) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        CustomizedToken token = new CustomizedToken(user.getUserName(), user.getPassword(), "user");
        JSONObject json = new JSONObject();
        try {
            subject.login(token);
            User realUser = userService.selectUserByName(user.getUserName());
            json.put("user", realUser);
            json.put("authToken", TokenUtil.createToken(realUser.getId().toString()));
            return Result.create(HttpCode.OK, Message.LOGIN_SUCCESS, json);
        } catch (UnknownAccountException e) {
            return Result.create(HttpCode.BAD_REQUEST, Message.USER_NOT_EXISTS);
        } catch (IncorrectCredentialsException e) {
            return Result.create(HttpCode.BAD_REQUEST, Message.PASSWORD_ERROR);
        } catch (AuthenticationException e) {
            return Result.create(HttpCode.BAD_REQUEST, Message.AUTHENTICATION_FAILED);
        }
    }
}
