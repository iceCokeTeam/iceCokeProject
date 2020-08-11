package com.shop.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Integer id;
    private Integer userId;
    private Integer orderNum;
    private String address;
    private String phone;
    private String name;
    private String remark;
    private Double freight;
    private Date payTime;
    private String payNum;
    private Integer orderStatus;
    private Double payMoney;
    private Integer payType;
    private Date SuccessTime;
    private Date sendTime;
    private Date createTime;
    private Date updateTime;

    private User user;
    private OrderDetail orderDetail;
}
