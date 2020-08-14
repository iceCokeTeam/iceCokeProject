package com.shop.service.impl;

import com.shop.dto.UserDTO;
import com.shop.mapper.UserMapper;
import com.shop.pojo.User;
import com.shop.service.UserService;
import com.shop.utils.ConstantUtil;
import com.shop.utils.RegexUtil;
import com.shop.utils.TimeUtil;
import com.shop.vo.UserVO;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.file.AccessMode;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    public Integer registerUser(User user) {
        if (selectUserByName(user.getUserName()) != null)
            return ConstantUtil.FAILED;
        Md5Hash hash = new Md5Hash(user.getPassword(), "abc", 1024);
        user.setPassword(hash.toHex());
        if (user.getNickName() == null){
            user.setNickName(user.getUserName());
        }
        if (user.getUserImg() == null) {
            user.setUserImg("https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2534506313,1688529724&fm=26&gp=0.jpg");
        }
        if (user.getSex() == null){
            user.setSex("男");
        }
        if (user.getAddress() == null) {
            user.setAddressId(-1);
        }
        if (user.getStatus() == null) {
            user.setStatus(1);
        }
        if (user.getCreateTime() == null) {
            user.setCreateTime(new Date());
        }
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

    @Override
    public User selectUser(Integer id) {
        return userMapper.selectUserById(id);
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
        userVO.setCreateTime(TimeUtil.dateFormat(user.getCreateTime()));
        return userVO;
    }

}
