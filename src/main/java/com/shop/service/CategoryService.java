package com.shop.service;

import com.shop.pojo.Category;

import java.util.List;

public interface CategoryService {

    List<Category> selectCategoryListByType(String categoryName, String parentId);

    int insertCategory(Category category);

    int deleteCategory(String id);

    int updateCategory(Category category);
}
