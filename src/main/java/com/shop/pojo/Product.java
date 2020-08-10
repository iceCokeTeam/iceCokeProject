package com.shop.pojo;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;

@Data
public class Product {
    private Integer id;
    private Integer categoryId;
    private Integer brandId;
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

    private Category category;
    private Brand brand;
}
