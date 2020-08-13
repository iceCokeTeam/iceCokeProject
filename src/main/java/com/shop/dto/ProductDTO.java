package com.shop.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Integer id;
    private String productName;
    private String productImg;
    private String subtitle;
    private String introduce;
    private String price;
    private String stock;
    private String measure;
    private String status;
    private Integer pageIndex;
    private Integer pageSize;
    private String priceSort;
    private String stockSort;
    private String createTimeSort;
    private String categoryId;
    private String brandId;
}
