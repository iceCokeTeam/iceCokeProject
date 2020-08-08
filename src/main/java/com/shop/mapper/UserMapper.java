package com.shop.mapper;

import com.shop.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int registerUser(User user);

    List<User> selectUserList();

    User selectUserByName(@Param("userName") String userName);
}
