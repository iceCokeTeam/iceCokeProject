package com.shop.service.impl;

import com.shop.dto.UserDTO;
import com.shop.mapper.UserMapper;
import com.shop.pojo.User;
import com.shop.service.UserService;
import com.shop.utils.ConstantUtil;
import com.shop.utils.RegexUtil;
import com.shop.vo.UserVO;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.file.AccessMode;
import java.util.ArrayList;
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
        Md5Hash hash = new Md5Hash(user.getPassword(), "abc", 1024);
        user.setPassword(hash.toHex());
        return userMapper.registerUser(user);
    }

    @Override
    public List<UserVO> selectUserList(UserDTO userDTO) {
        Map<String, Object> map = new HashMap<>();
        map.put("userName", userDTO.getUserName());
        map.put("status", userDTO.getStatus());
        map.put("pageIndex", userDTO.getPageIndex());
        map.put("pageSize", userDTO.getPageSize());
        List<User> users = userMapper.selectUserList(map);
        List<UserVO> userVOs = new ArrayList<>();
        for (User user : users) {
            userVOs.add(transformUserVO(user));
        }
        return userVOs;
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
    public UserVO selectUserById(String id) {
        if (RegexUtil.isDigital(id))
            return transformUserVO(userMapper.selectUserById(Integer.valueOf(id)));
        return null;
    }

    @Override
    public User selectUserByName(String userName) {
        return userMapper.selectUserByName(userName);
    }

    @Override
    public int userAmount(UserDTO userDTO) {
        Map<String, Object> map = new HashMap<>();
        map.put("userName", userDTO.getUserName());
        map.put("status", userDTO.getStatus());
        return userMapper.userAmount(map);
    }

    public UserVO transformUserVO(User user) {
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUserName(user.getUserName());
        userVO.setNickName(user.getNickName());
        userVO.setPhone(user.getPhone());
        userVO.setUserImg(user.getUserImg());
        userVO.setSex(user.getSex());
        userVO.setStatus(user.getStatus());
        userVO.setAddress(user.getAddress());
        userVO.setCreateTime(user.getCreateTime());
        return userVO;
    }

}
