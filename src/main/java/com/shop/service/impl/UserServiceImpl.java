package com.shop.service.impl;

import com.shop.mapper.UserMapper;
import com.shop.pojo.User;
import com.shop.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    public Integer registerUser(User user) {
        Md5Hash hash = new Md5Hash(user.getPassword(), countUser().toString(), 1024);
        user.setPassword(hash.toHex());
        return userMapper.registerUser(user);
    }

    @Override
    public List<User> selectUserList() {
        return userMapper.selectUserList();
    }

    @Override
    public Integer countUser() {
        return userMapper.selectUserList().size();
    }

}
