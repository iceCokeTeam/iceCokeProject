package com.shop.service;

import com.shop.dto.CartDTO;
import com.shop.pojo.Cart;

import java.util.List;

public interface CartService {

    List<Cart> selectCartList(String userId);

    int insertCart(CartDTO cartDTO);

    int deleteCart(List<Integer> carts);

    int updateCart(CartDTO cartDTO);
}
