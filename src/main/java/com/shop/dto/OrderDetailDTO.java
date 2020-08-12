package com.shop.dto;

import lombok.Data;

@Data
public class OrderDetailDTO {
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private Integer num;
    private Double price;
    private Double totalPrice;
    private String productName;
    private String productImg;
    private Integer status;
}
