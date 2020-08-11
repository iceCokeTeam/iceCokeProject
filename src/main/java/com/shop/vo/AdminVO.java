package com.shop.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AdminVO {
    private Integer id;
    private String adminName;
    private String nickName;
    private String email;
    private Integer status;
    private String adminImg;
}
