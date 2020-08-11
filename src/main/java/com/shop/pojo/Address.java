package com.shop.pojo;

import lombok.Data;

@Data
public class Address {
    private Integer id;
    private Integer userId;
    private String receiverName;
    private String receiverPhone;
    private String province;
    private String city;
    private String area;
    private String place;
}
