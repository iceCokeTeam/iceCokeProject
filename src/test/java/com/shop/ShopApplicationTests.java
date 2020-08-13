package com.shop;

import com.shop.mapper.*;
import com.shop.pojo.*;
import com.shop.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;


@SpringBootTest
class ShopApplicationTests {
    @Resource
    private CategoryService categoryService;
    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private AddressMapper addressMapper;

    @Resource
    private CartMapper cartMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Test
    public void testUser() {
        System.out.println(userMapper.selectUserById(1));
    }

    @Test
    public void testCategory() {
        Category category = new Category();
        category.setParentId(0);
        Map<String, Object> map = new HashMap<>();
        map.put("parentId", 0);
        System.out.println(categoryMapper.categoryAmount(map));
    }

    @Test
    public void testCart() {
        Cart cart = new Cart();
        List<Integer> carts = new ArrayList<>();
        carts.add(10);
        System.out.println(cartMapper.deleteCart(carts));
    }

    @Test
    public void testOrderDetail() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setTotalPrice(100.0);
        orderDetail.setStatus(1);
        orderDetail.setProductName("hello");
        orderDetail.setPrice(10.0);
        orderDetail.setOrderId(1);
        orderDetail.setNum(23);
        orderDetail.setCreateTime(new Date());
        orderDetail.setUpdateTime(new Date());
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(orderDetail);
        System.out.println(orderDetailMapper.insertOrderDetail(orderDetails));
    }

}
