package com.shop.mapper;

import com.shop.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int registerUser(User user);

    List<User> selectUserList();
}
