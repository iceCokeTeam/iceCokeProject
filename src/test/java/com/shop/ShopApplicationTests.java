package com.shop;

import com.auth0.jwt.interfaces.Claim;
import com.shop.utils.TokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


@SpringBootTest
class ShopApplicationTests {

    @Test
    void contextLoads() throws Exception {
//        String token = TokenUtil.createToken("1");
//        System.out.println(token);
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJBUFAiLCJpc3MiOiJTZXJ2aWNlIiwiZXhwIjoxNTk2ODUwNjMwLCJ1c2VySWQiOiIxIiwiaWF0IjoxNTk2ODUwNjIwfQ.emEACsc4aN72iukc6p-73ZHY8DpRxO-r-Mm-nv5JMA4";
        Map<String, Claim> stringClaimMap = TokenUtil.verifyToken(token);
        System.out.println(stringClaimMap);

    }

}
