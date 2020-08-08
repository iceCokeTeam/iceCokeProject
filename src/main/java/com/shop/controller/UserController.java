package com.shop.controller;

import com.shop.pojo.User;
import com.shop.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Map<String, Object> registerUser(User user) {
        int suss = userService.registerUser(user);
        Map<String, Object> map = new HashMap<>();
        if (suss == 1) {
            map.put("status", 0);
            map.put("msg", "注册成功");
        }
        else{
            map.put("status", 1);
            map.put("msg", "注册失败");
        }
        return map;
    }
}
