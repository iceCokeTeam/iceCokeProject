package com.shop.mapper;

import com.shop.pojo.Order;
import com.shop.pojo.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    List<OrderDetail> selectOrderDetailList(Integer orderId);

    OrderDetail selectOrderDetailById(Integer id);

    int insertOrderDetail(List<OrderDetail> orderDetails);

    int updateOrderDetail(OrderDetail orderDetail);

    int deleteOrderDetailByOrderId(Integer orderId);
}
