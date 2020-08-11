package com.shop.mapper;

import com.shop.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    List<Order> selectOrderList(Map<String, Object> map);

    Order selectOrderById(Integer id);

    int insertOrder(Order order);

    int updateOrder(Order order);

    int deleteOrder(Integer id);
}
