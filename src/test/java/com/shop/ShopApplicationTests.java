package com.shop;

import com.shop.dto.ProductDTO;
import com.shop.mapper.CategoryMapper;
import com.shop.mapper.ProductMapper;
import com.shop.pojo.Product;
import com.shop.service.CategoryService;
import com.shop.utils.TokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    @Test
    void contextLoads() throws Exception {
//        String token = TokenUtil.createToken("8");
//        System.out.println(token);
        String str = "hello";
        System.out.println(str.substring(0, 1).toUpperCase() + str.substring(1));

    }

    @Test
    void test() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        Class cls = productDTO.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            String str = field.getName();
            System.out.println(str);
            Method m = productDTO.getClass().getMethod("get" + str.substring(0, 1).toUpperCase() + str.substring(1));
            Object value = m.invoke(productDTO);
            System.out.println(value);
        }

    }

}
