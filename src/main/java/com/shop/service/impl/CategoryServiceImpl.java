package com.shop.service.impl;

import com.shop.dto.CategoryDTO;
import com.shop.mapper.CategoryMapper;
import com.shop.pojo.Category;
import com.shop.service.CategoryService;
import com.shop.utils.ConstantUtil;
import com.shop.utils.RegexUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> selectCategoryList(CategoryDTO categoryDTO) {
        Map<String, Object> map = new HashMap<>();
        map.put("parentId", categoryDTO.getParentId());
        map.put("categoryName", categoryDTO.getCategoryName());
        map.put("updateTime", categoryDTO.getUpdateTime());
        map.put("pageIndex", categoryDTO.getPageIndex());
        map.put("pageSize", categoryDTO.getPageSize());
        if (RegexUtil.isDigital(categoryDTO.getParentId()))
            return categoryMapper.selectCategoryList(map);
        map.remove("parentId");
        return categoryMapper.selectCategoryList(map);
    }

    @Override
    public int insertCategory(Category category) {
        if (judge(category))
            return ConstantUtil.FAILED;
        Map<String, Object> map = new HashMap<>();
        map.put("categoryName", category.getCategoryName());
        if (categoryMapper.selectCategoryList(map).size() > 0)
            return ConstantUtil.FAILED;
        category.setCreateTime(new Date());
        category.setUpdateTime(new Date());
        return categoryMapper.insertCategory(category);
    }

    @Override
    public int deleteCategory(String id) {
        if (RegexUtil.isDigital(id)) {
            return categoryMapper.deleteCategory(Integer.valueOf(id));
        }
        return ConstantUtil.FAILED;
    }

    @Override
    public int updateCategory(Category category) {
        if (judge(category))
            return ConstantUtil.FAILED;
        category.setUpdateTime(new Date());
        return categoryMapper.updateCategory(category);
    }

    @Override
    public int categoryAmount() {
        return categoryMapper.categoryAmount();
    }

    public boolean judge(Category category) {
        return category.getCategoryName() == null || category.getParentId() == null || category.getCategoryName() == "";
    }
}
