package com.shop.vo;

import com.shop.pojo.Address;
import lombok.Data;

import java.util.Date;

@Data
public class UserVO {
    private Integer id;
    private String userName;
    private String nickName;
    private String phone;
    private String userImg;
    private String sex;
    private Integer status;
    private Address address;
    private Date createTime;
}
