package com.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.shop.pojo.Brand;
import com.shop.service.BrandService;
import com.shop.utils.HttpCode;
import com.shop.utils.Message;
import com.shop.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Resource
    private BrandService brandService;

    @PostMapping("/list")
    public Result brandList(String name) {
        JSONObject json = new JSONObject();
        json.put("data", brandService.selectBrandList(name));
        return Result.create(HttpCode.OK, Message.SELECT_SUCCESS, json);
    }

    @PostMapping("/add")
    public Result addBrand(Brand brand) {
        if (brandService.insertBrand(brand) == 1) {
            return Result.create(HttpCode.OK, Message.SELECT_SUCCESS);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.ADD_FAILED);
    }

    @PostMapping("/del")
    public Result delBrand(String id) {
        if (brandService.deleteBrand(id) == 1) {
            return Result.create(HttpCode.OK, Message.DEL_SUCCESS);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.DEL_SUCCESS);
    }


}
