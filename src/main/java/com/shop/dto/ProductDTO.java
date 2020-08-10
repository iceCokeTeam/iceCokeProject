package com.shop.dto;

import com.shop.pojo.Brand;
import com.shop.pojo.Category;
import lombok.Data;

import java.util.Date;

@Data
public class ProductDTO {
    private Integer id;
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
    private Integer pageIndex;
    private Integer pageSize;
    private String priceSort;
    private String stockSort;
    private String createTimeSort;
    private Category category;
    private Brand brand;
}
