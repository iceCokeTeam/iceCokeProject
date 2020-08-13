package com.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.shop.dto.ProductDTO;
import com.shop.pojo.Product;
import com.shop.service.ProductService;
import com.shop.utils.HttpCode;
import com.shop.utils.Message;
import com.shop.utils.Result;
import org.apache.shiro.session.ProxiedSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    @PostMapping("/list")
    public Result productList(ProductDTO productDTO) throws Exception {
        JSONObject json = new JSONObject();
        json.put("productList", productService.selectProductList(productDTO));
        json.put("productAmount", productService.productAmount(productDTO));
        return Result.create(HttpCode.OK, Message.SELECT_SUCCESS, json);
    }

    @PostMapping("/add")
    public Result addProduct(ProductDTO productDTO) {
        if (productService.insertProduct(productDTO) == 1) {
            return Result.create(HttpCode.OK, Message.ADD_SUCCESS);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.ADD_FAILED);
    }

    @PostMapping("/del")
    public Result delProduct(String id) {
        if (productService.deleteProduct(id) == 1) {
            return Result.create(HttpCode.OK, Message.DEL_SUCCESS);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.DEL_FAILED);
    }

    @PostMapping("/update")
    public Result updateProduct(ProductDTO productDTO) {
        if (productService.updateProduct(productDTO) == 1) {
            return Result.create(HttpCode.OK, Message.UPDATE_SUCCESS);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.UPDATE_FAILED);
    }
}
