package com.shop.service;

import com.shop.dto.UserDTO;
import com.shop.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    Integer registerUser(User user);

    List<User> selectUserList(UserDTO userDTO);

    int updateUser(User user);

    int deleteUser(String id);

    User selectUserById(String id);

    User selectUserByName(String userName);

}
