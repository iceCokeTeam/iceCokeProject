package com.shop.vo;

import com.shop.pojo.Category;
import lombok.Data;

import java.util.Date;

@Data
public class CategoryVO {
    private Integer id;
    private String categoryName;
    private String createTime;
    private String updateTime;
    private Integer pageIndex;
    private Integer pageSize;

    private Category parentCategory;
}
