package com.shop;

import com.shop.mapper.CategoryMapper;
import com.shop.mapper.ProductMapper;
import com.shop.pojo.Category;
import com.shop.pojo.Product;
import com.shop.service.CategoryService;
import com.shop.utils.RegexUtil;
import com.shop.utils.TokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
class ShopApplicationTests {
    @Resource
    private CategoryService categoryService;
    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private ProductMapper productMapper;

    @Test
    void contextLoads() throws Exception {
        String token = TokenUtil.createToken("8");
        System.out.println(token);

    }

    @Test
    void test() {
        Product product = new Product();
        Map<String, Object> map = new HashMap<>();
        map.put("priceSort", "desc");
        map.put("pageSize", 1);
        map.put("pageNum", 1);
        for (Product product1 : productMapper.selectProductList(map)) {
            System.out.println(product1);
        }

    }

}
