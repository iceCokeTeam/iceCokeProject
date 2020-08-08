package com.shop.service;

import com.shop.pojo.User;

import java.util.List;

public interface UserService {
    Integer registerUser(User user);

    List<User> selectUserList();

    Integer countUser();

    User selectUserByName(String userName);
}
