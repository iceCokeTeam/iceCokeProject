package com.shop.dto;

import lombok.Data;

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
    private Integer pageIndex;
    private Integer pageSize;
    private String priceSort;
    private String stockSort;
    private String createTimeSort;
    private Integer categoryId;
    private Integer brandId;
}
