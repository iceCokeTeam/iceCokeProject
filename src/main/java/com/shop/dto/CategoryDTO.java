package com.shop.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryDTO {
    private Integer id;
    private Integer parentId;
    private String categoryName;
    private Date createTime;
    private Date updateTime;
    private Integer pageIndex;
    private Integer pageSize;
}
