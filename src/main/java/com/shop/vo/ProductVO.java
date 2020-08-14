package com.shop.vo;

import com.shop.pojo.Brand;
import com.shop.pojo.Category;
import lombok.Data;

import java.util.Date;

@Data
public class ProductVO {
    private Integer id;
    private String productName;
    private String productImg;
    private String subtitle;
    private String introduce;
    private Double price;
    private Integer stock;
    private String measure;
    private Integer status;
    private String createTime;
    private String updateTime;
    private Category category;
    private Brand brand;
}
