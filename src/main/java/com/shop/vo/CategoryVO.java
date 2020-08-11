package com.shop.vo;

import com.shop.pojo.Category;
import lombok.Data;

import java.util.Date;

@Data
public class CategoryVO {
    private Integer id;
    private String categoryName;
    private Date createTime;
    private Date updateTime;
    private Integer pageIndex;
    private Integer pageSize;

    private Category parentCategory;
}
