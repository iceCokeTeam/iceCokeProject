package com.shop.service.impl;

import com.shop.mapper.CategoryMapper;
import com.shop.pojo.Category;
import com.shop.service.CategoryService;
import com.shop.utils.RegexUtil;
import com.shop.utils.TimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> selectCategoryListByType(String categoryName, String parentId) {
        if (parentId == null || parentId == "")
            return categoryMapper.selectCategoryListByType(categoryName, null);
        else if (RegexUtil.isDigital(parentId))
            return categoryMapper.selectCategoryListByType(categoryName, Integer.valueOf(parentId));
        return null;
    }

    @Override
    public int insertCategory(Category category) {
        if (judge(category))
            return 0;
        if (categoryMapper.selectCategoryListByType(category.getCategoryName(), null).size() > 0)
            return 0;
        category.setCreateTime(new Date());
        return categoryMapper.insertCategory(category);
    }

    @Override
    public int deleteCategory(String id) {
        if (RegexUtil.isDigital(id)) {
            return 0;
        }
        return categoryMapper.deleteCategory(Integer.valueOf(id));
    }

    @Override
    public int updateCategory(Category category) {
        if (judge(category))
            return 0;
        category.setUpdateTime(new Date());
        return categoryMapper.updateCategory(category);
    }

    public boolean judge(Category category) {
        return category.getCategoryName() == null || category.getParentId() == null || category.getCategoryName() == "";
    }
}
