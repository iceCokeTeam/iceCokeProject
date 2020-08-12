package com.shop.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AdminDTO {
    private Integer id;
    private String adminName;
    private String password;
    private String nickName;
    private String email;
    private Date createTime;
    private Date loginTime;
    private Integer status;
    private String adminImg;
    private Integer pageIndex;
    private Integer pageSize;
}
