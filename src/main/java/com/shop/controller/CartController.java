package com.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.shop.dto.CartDTO;
import com.shop.pojo.Cart;
import com.shop.service.CartService;
import com.shop.utils.HttpCode;
import com.shop.utils.Message;
import com.shop.utils.Result;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Resource
    private CartService cartService;

    @PostMapping("/list")
    public Result cartList(String userId) {
        List<Cart> carts = cartService.selectCartList(userId);
        if (carts != null) {
            JSONObject json = new JSONObject();
            json.put("data", carts);
            return Result.create(HttpCode.OK, Message.SELECT_SUCCESS, json);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.SELECT_FAILED);
    }

    @PostMapping("/add")
    public Result addCart(CartDTO cartDTO) {
        if (cartService.insertCart(cartDTO) == 1)
            return Result.create(HttpCode.OK, Message.ADD_SUCCESS);
        return Result.create(HttpCode.BAD_REQUEST, Message.ADD_FAILED);
    }

    @PostMapping("/del")
    public Result delCart(List<Integer> carts) {
        if (cartService.deleteCart(carts) > 0) {
            return Result.create(HttpCode.OK, Message.DEL_SUCCESS);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.DEL_FAILED);
    }

    @PostMapping("/update")
    public Result updateCart(CartDTO cartDTO) {
        if (cartService.updateCart(cartDTO) == 1) {
            return Result.create(HttpCode.OK, Message.UPDATE_SUCCESS);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.UPDATE_FAILED);
    }
}
