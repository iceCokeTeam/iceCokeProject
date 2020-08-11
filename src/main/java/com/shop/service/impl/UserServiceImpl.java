package com.shop.service.impl;

import com.shop.dto.UserDTO;
import com.shop.mapper.UserMapper;
import com.shop.pojo.User;
import com.shop.service.UserService;
import com.shop.utils.ConstantUtil;
import com.shop.utils.RegexUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    public Integer registerUser(User user) {
        if (selectUserByName(user.getUserName()) != null)
            return ConstantUtil.FAILED;
        Md5Hash hash = new Md5Hash(user.getPassword(), "def", 1024);
        user.setPassword(hash.toHex());
        return userMapper.registerUser(user);
    }

    @Override
    public List<User> selectUserList(UserDTO userDTO) {
        Map<String, Object> map = new HashMap<>();
        map.put("pageIndex", userDTO.getPageIndex());
        return userMapper.selectUserList(map);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUser(String id) {
        if (RegexUtil.isDigital(id))
            return userMapper.deleteUser(Integer.valueOf(id));
        return ConstantUtil.FAILED;
    }

    @Override
    public User selectUserById(String id) {
        if (RegexUtil.isDigital(id))
            return userMapper.selectUserById(Integer.valueOf(id));
        return null;
    }

    @Override
    public User selectUserByName(String userName) {
        return userMapper.selectUserByName(userName);
    }

}
