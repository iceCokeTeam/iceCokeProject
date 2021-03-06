package com.shop.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order {
    private Integer id;
    private Integer userId;
    private String orderNum;
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
    private Date successTime;
    private Date sendTime;
    private Date createTime;
    private Date updateTime;

    private User user;
    private List<OrderDetail> orderDetail;
}
