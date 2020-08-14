package com.shop.vo;

import com.shop.pojo.OrderDetail;
import com.shop.pojo.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderVO {
    private Integer id;
    private String orderNum;
    private String address;
    private String phone;
    private String name;
    private String remark;
    private Double freight;
    private String payTime;
    private String payNum;
    private Integer orderStatus;
    private Double payMoney;
    private Integer payType;
    private String successTime;
    private String sendTime;
    private String createTime;
    private String updateTime;

    private UserVO userVO;
    private List<OrderDetail> orderDetail;
}
