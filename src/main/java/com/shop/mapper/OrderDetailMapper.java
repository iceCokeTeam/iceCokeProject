package com.shop.mapper;

import com.shop.pojo.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    List<OrderDetail> selectOrderDetailList(Integer id);

    OrderDetail selectOrderDetailById(Integer id);

    int insertOrderDetail(List<OrderDetail> orderDetails);

    int updateOrderDetail(OrderDetail orderDetail);

    int deleteOrderDetailByOrderId(Integer orderId);
}
