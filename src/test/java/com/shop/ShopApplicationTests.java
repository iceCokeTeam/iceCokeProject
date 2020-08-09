package com.shop;

import com.shop.utils.TokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ShopApplicationTests {

    @Test
    void contextLoads() throws Exception {
        String token = TokenUtil.createToken("8");
        System.out.println(token);

    }

    @Test
    void test() {
        String url = "/admin/test";
        boolean b = url.matches(".*admin.*");
        System.out.println(b);
    }

}
