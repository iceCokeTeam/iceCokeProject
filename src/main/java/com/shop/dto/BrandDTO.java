package com.shop.dto;

import lombok.Data;

@Data
public class BrandDTO {
    private Integer id;
    private String brandName;
    private String logo;
    private String introduce;
    private Integer pageIndex;
    private Integer pageSize;
}
