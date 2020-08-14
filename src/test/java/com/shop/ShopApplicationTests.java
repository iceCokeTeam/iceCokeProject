package com.shop;

import com.shop.dto.BrandDTO;
import com.shop.dto.CategoryDTO;
import com.shop.service.BrandService;
import com.shop.service.CategoryService;
import com.shop.utils.OrderNumUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootTest
class ShopApplicationTests {

    @Resource
    private CategoryService categoryService;

    @Resource
    private BrandService brandService;

    @Test
    public void test() {
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setBrandName("索尼");
        System.out.println(brandService.brandAmount(brandDTO));

    }


}
