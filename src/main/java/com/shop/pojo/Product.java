package com.shop.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Product {
    private Integer id;
    private Category category;
    private Brand brand;
    private String productName;
    private String productImg;
    private String subtitle;
    private String introduce;
    private Double price;
    private Integer stock;
    private String measure;
    private Integer status;
    private Date createTime;
    private Date updateTime;

}
