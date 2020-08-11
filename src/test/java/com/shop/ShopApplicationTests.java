package com.shop;

import com.shop.mapper.AddressMapper;
import com.shop.mapper.CategoryMapper;
import com.shop.mapper.ProductMapper;
import com.shop.mapper.UserMapper;
import com.shop.pojo.Address;
import com.shop.pojo.User;
import com.shop.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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
    private UserMapper userMapper;

    @Test
    public void testUser() {
        System.out.println(userMapper.selectUserById(1));
    }

}
