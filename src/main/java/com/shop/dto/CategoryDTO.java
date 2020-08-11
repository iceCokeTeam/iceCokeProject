package com.shop.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryDTO {
    private Integer id;
    private String parentId;
    private String categoryName;
    private Date createTime;
    private Date updateTime;
    private String pageIndex;
    private String pageSize;
}
