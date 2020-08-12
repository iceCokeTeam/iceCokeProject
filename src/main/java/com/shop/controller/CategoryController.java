package com.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.shop.dto.CategoryDTO;
import com.shop.pojo.Category;
import com.shop.service.CategoryService;
import com.shop.utils.HttpCode;
import com.shop.utils.Message;
import com.shop.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @PostMapping("/add")
    public Result addCategory(Category category) {
        if (categoryService.insertCategory(category) == 1) {
            return Result.create(HttpCode.OK, Message.ADD_SUCCESS);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.ADD_FAILED);
    }

    @PostMapping("/del")
    public Result delCategory(String id) {
        if (categoryService.deleteCategory(id) == 1)
            return Result.create(HttpCode.OK, Message.DEL_SUCCESS);
        return Result.create(HttpCode.BAD_REQUEST, Message.DEL_FAILED);
    }

    @PostMapping("/update")
    public Result updateCategory(Category category) {
        if (categoryService.updateCategory(category) == 1)
            return Result.create(HttpCode.OK, Message.DEL_SUCCESS);
        return Result.create(HttpCode.BAD_REQUEST, Message.DEL_FAILED);
    }

    @PostMapping("/list")
    public Result categoryList(CategoryDTO categoryDTO) {
        List<Category> categoryList = categoryService.selectCategoryList(categoryDTO);
        JSONObject json = new JSONObject();
        if (categoryList != null) {
            json.put("categoryList", categoryList);
            json.put("categoryAmount", categoryService.categoryAmount());
            return Result.create(HttpCode.OK, Message.SELECT_SUCCESS, json);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.SELECT_FAILED);
    }
}
