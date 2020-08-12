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
import com.shop.vo.UserVO;
import org.apache.ibatis.annotations.Update;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

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
        List<UserVO> userVOs = userService.selectUserList(userDTO);
        if (userVOs == null)
            return Result.create(HttpCode.BAD_REQUEST, Message.SELECT_FAILED);
        JSONObject json = new JSONObject();
        json.put("userList", userVOs);
        json.put("userAmount", userService.userAmount());
        return Result.create(HttpCode.OK, Message.SELECT_SUCCESS, json);
    }

    @PostMapping("/id")
    public Result selectUserById(String id) {
        UserVO userVO = userService.selectUserById(id);
        JSONObject json = new JSONObject();
        if (userVO != null){
            json.put("user", userVO);
            return Result.create(HttpCode.OK, Message.SELECT_SUCCESS, json);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.SELECT_FAILED);
    }

    @PostMapping("/update")
    public Result updateUser(User user) {
        if (userService.updateUser(user) == 1)
            return Result.create(HttpCode.OK, Message.UPDATE_SUCCESS);
        return Result.create(HttpCode.BAD_REQUEST, Message.UPDATE_FAILED);
    }

    @PostMapping("/del")
    public Result delUser(String id) {
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
