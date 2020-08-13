package com.shop.service;

import com.shop.dto.UserDTO;
import com.shop.pojo.User;
import com.shop.vo.UserVO;

import java.util.List;
import java.util.Map;

public interface UserService {
    Integer registerUser(User user);

    List<UserVO> selectUserList(UserDTO userDTO);

    int updateUser(User user);

    int deleteUser(String id);

    UserVO selectUserById(String id);

    User selectUserByName(String userName);

    int userAmount(UserDTO userDTO);

}
