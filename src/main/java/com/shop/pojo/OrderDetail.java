package com.shop.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDetail {
    private Integer id;
    private Integer orderId;
    private Integer num;
    private Integer productId;
    private Double price;
    private Double totalPrice;
    private String productName;
    private String productImg;
    private Integer status;
    private Date createTime;
    private Date updateTime;
}
