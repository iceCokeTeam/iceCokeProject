package com.shop.mapper;

import com.shop.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CategoryMapper {

    List<Category> selectCategoryList(Map<String, Object> map);

    int insertCategory(Category category);

    int deleteCategory(Integer id);

    int updateCategory(Category category);

    Category selectCategoryById(Integer id);

}
