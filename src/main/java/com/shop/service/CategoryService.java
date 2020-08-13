package com.shop.service;

import com.shop.dto.CategoryDTO;
import com.shop.pojo.Category;

import java.util.List;

public interface CategoryService {

    List<Category> selectCategoryList(CategoryDTO categoryDTO);

    int insertCategory(Category category);

    int deleteCategory(String id);

    int updateCategory(Category category);

    int categoryAmount(CategoryDTO categoryDTO);

    List<Category> allCategoryLevel();
}
