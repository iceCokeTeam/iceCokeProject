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
    private Date payTime;
    private String payNum;
    private Integer orderStatus;
    private Double payMoney;
    private Integer payType;
    private Date successTime;
    private Date sendTime;
    private Date createTime;
    private Date updateTime;

    private UserVO userVO;
    private List<OrderDetail> orderDetail;
}
