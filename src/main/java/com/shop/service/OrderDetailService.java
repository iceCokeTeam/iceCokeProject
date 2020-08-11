package com.shop.service;

import com.shop.dto.OrderDetailDTO;
import com.shop.pojo.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> selectOrderDetailList(String orderId);

    OrderDetail selectOrderDetailById(String id);

    int insertOrderDetail(List<OrderDetailDTO> orderDetailDTOs);

    int updateOrderDetail(OrderDetailDTO orderDetailDTO);

    int deleteOrderDetailByOrderId(String orderId);
}
