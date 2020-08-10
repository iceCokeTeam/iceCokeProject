package com.shop.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Category {
    private Integer id;
    private Integer parentId;
    private String categoryName;
    private Date createTime;
    private Date updateTime;
}
