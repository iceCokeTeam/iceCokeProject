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
import java.util.List;


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
        Category category = new Category();
        category.setId(1);
        Product product = new Product();
        product.setCategoryId(1);
        product.setBrandId(1);
        product.setCategory(category);
        System.out.println(categoryMapper.selectCategoryById(1));
        System.out.println(productMapper.selectProduct(product));

    }

}
