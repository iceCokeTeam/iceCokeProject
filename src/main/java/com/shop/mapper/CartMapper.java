package com.shop.mapper;

import com.shop.pojo.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    List<Cart> selectCartList(Integer userId);

    int insertCart(Cart cart);

    int deleteCart(List<Integer> carts);

    int updateCart(Cart cart);

    Cart selectCartByProductId(Cart cart);

}
