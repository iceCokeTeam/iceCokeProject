package com.shop.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Admin {
    private Integer id;
    private String adminName;
    private String password;
    private String name;
    private String email;
    private Date createTime;
    private Date loginTime;
    private Integer status;
    private String adminImg;
}
