package com.shop.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Category {
    private Integer id;
    private Integer parentId;
    private String categoryName;
    private Date createTime;
    private Date updateTime;

    private List<Category> category;
}
