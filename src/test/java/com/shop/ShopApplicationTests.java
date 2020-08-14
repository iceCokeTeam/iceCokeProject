package com.shop;

import com.shop.utils.OrderNumUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootTest
class ShopApplicationTests {

    @Test
    public void test() {
        System.out.println(OrderNumUtil.GetRandom());

    }


}
