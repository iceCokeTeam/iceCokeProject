package com.shop.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String userName;
    private String nickName;
    private String password;
    private String phone;
    private String userImg;
    private String sex;
    private Integer status;
    private Integer addressId;
    private Date createTime;
    private Date updateTime;

    private Address address;

}
