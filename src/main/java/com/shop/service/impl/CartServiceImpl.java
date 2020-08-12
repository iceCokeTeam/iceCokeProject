package com.shop.service.impl;

import com.shop.dto.CartDTO;
import com.shop.mapper.CartMapper;
import com.shop.pojo.Cart;
import com.shop.service.CartService;
import com.shop.utils.ConstantUtil;
import com.shop.utils.RegexUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Resource
    private CartMapper cartMapper;

    @Override
    public List<Cart> selectCartList(String userId) {
        if (RegexUtil.isDigital(userId))
            return cartMapper.selectCartList(Integer.valueOf(userId));
        return null;
    }

    @Override
    public int insertCart(CartDTO cartDTO) {
        if (cartDTO.getUserId() == null || cartDTO.getProductId() == null || cartDTO.getQuantity() == null) {
            return ConstantUtil.FAILED;
        }
        Cart cart = new Cart();
        cart.setProductId(cartDTO.getProductId());
        cart.setQuantity(cartDTO.getQuantity());
        cart.setUserId(cartDTO.getUserId());
        Cart c = cartMapper.selectCartByProductId(cart);
        if (c != null) {
            cart.setId(c.getId());
            cart.setUpdateTime(new Date());
            return cartMapper.updateCart(cart);
        }
        cart.setCreateTime(new Date());
        cart.setUpdateTime(new Date());
        return cartMapper.insertCart(cart);
    }

    @Override
    public int deleteCart(List<Integer> carts) {
        return cartMapper.deleteCart(carts);
    }

    @Override
    public int updateCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setProductId(cartDTO.getProductId());
        cart.setQuantity(cartDTO.getQuantity());
        cart.setUserId(cartDTO.getUserId());
        cart.setUpdateTime(new Date());
        return cartMapper.updateCart(cart);
    }
}
