package com.shop.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Integer id;
    private Integer userId;
    private String address;
    private String phone;
    private String name;
    private String orderNum;
    private String remark;
    private Double freight;
    private Integer orderStatus;
    private Double payMoney;
    private Integer payType;
    private Integer pageIndex;
    private Integer pageSize;
}
