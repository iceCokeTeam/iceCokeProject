package com.shop;

import com.shop.mapper.CategoryMapper;
import com.shop.pojo.Category;
import com.shop.service.CategoryService;
import com.shop.utils.RegexUtil;
import com.shop.utils.TokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@SpringBootTest
class ShopApplicationTests {
    @Resource
    private CategoryService categoryService;
    @Resource
    private CategoryMapper categoryMapper;

    @Test
    void contextLoads() throws Exception {
        String token = TokenUtil.createToken("8");
        System.out.println(token);

    }

    @Test
    void test() {
        String s = "";
        System.out.println(RegexUtil.isDigital(s));


    }

}
