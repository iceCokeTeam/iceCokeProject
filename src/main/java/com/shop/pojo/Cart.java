package com.shop.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Cart {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private Date createTime;
    private Date updateTime;

    private Product product;
}
