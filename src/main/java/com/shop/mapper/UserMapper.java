package com.shop.mapper;

import com.shop.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    int registerUser(User user);

    List<User> selectUserList(Map<String, Object> map);

    int updateUser(User user);

    int deleteUser(Integer id);

    User selectUserById(Integer id);

    User selectUserByName(String userName);

    int userAmount();
}
