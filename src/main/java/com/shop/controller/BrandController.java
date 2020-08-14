package com.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.shop.dto.BrandDTO;
import com.shop.pojo.Brand;
import com.shop.service.BrandService;
import com.shop.utils.HttpCode;
import com.shop.utils.Message;
import com.shop.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Resource
    private BrandService brandService;

    @PostMapping("/list")
    public Result brandList(BrandDTO brandDTO) {
        JSONObject json = new JSONObject();
        json.put("brandList", brandService.selectBrandList(brandDTO));
        json.put("brandAmount", brandService.brandAmount(brandDTO));
        return Result.create(HttpCode.OK, Message.SELECT_SUCCESS, json);
    }

    @PostMapping("/add")
    public Result addBrand(Brand brand) {
        if (brandService.insertBrand(brand) == 1) {
            return Result.create(HttpCode.OK, Message.ADD_SUCCESS);
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

    @PostMapping("/brandName")
    public Result selectBrandByName(String brandName) {
        List<Brand> brands = brandService.selectBrandByName(brandName);
        JSONObject json = new JSONObject();
        json.put("brand", brands);
        return Result.create(HttpCode.OK, Message.SELECT_SUCCESS, json);
    }

    @PostMapping("/update")
    public Result updateBrand(Brand brand){
        if (brandService.updateBrand(brand) == 1) {
            return Result.create(HttpCode.OK, Message.UPDATE_SUCCESS);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.UPDATE_FAILED);
    }

}
