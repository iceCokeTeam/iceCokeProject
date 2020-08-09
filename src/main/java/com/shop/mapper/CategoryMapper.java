package com.shop.mapper;

import com.shop.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> selectCategoryListByType(String name, Integer parentId);

    int insertCategory(Category category);

    int deleteCategory(int id);

    int updateCategory(Category category);

}