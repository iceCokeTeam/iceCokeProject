package com.shop.service;

import com.shop.dto.OrderDTO;
import com.shop.pojo.Order;
import com.shop.vo.OrderVO;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<OrderVO> selectOrderList(OrderDTO orderDTO);

    Order selectOrderById(String id);

    int insertOrder(OrderDTO orderDTO);

    int updateOrder(OrderDTO orderDTO);

    int deleteOrder(String id);

    int orderAmount(OrderDTO orderDTO);
}
