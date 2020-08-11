package com.shop.mapper;

import com.shop.pojo.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    List<Cart> selectCartList(Integer userId);

    int insertCart(Cart cart);

    int deleteCart(Integer id);

    int updateCart(Cart cart);
}
