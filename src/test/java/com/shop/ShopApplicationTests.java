package com.shop;

import com.shop.dto.OrderDTO;
import com.shop.mapper.*;
import com.shop.pojo.*;
import com.shop.service.CategoryService;
import com.shop.service.OrderService;
import com.shop.service.ProductService;
import com.shop.vo.OrderVO;
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
    private ProductService productService;

    @Resource
    private AddressMapper addressMapper;

    @Resource
    private CartMapper cartMapper;

    @Resource
    private OrderService orderService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Resource
    private OrderMapper orderMapper;

    @Test
    public void testUser() {
        System.out.println(userMapper.selectUserById(1));
    }

    @Test
    public void testCategory() {
        System.out.println(categoryMapper.allCategoryLevel());
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
        System.out.println(orderDetailMapper.selectOrderDetailList(3));
    }

    @Test
    public void testProduct() {
//        System.out.println(categoryMapper.selectCategoryById(1));
        System.out.println(productService.selectProductById("3"));


    }

    @Test
    public void testOrder() {
        Map<String, Object> map = new HashMap<>();
        OrderDTO orderDTO = new OrderDTO();
        List<Order> orders = orderMapper.selectOrderList(map);
        for (Order order : orders) {
            System.out.println(order.getUser());
        }

    }


}
