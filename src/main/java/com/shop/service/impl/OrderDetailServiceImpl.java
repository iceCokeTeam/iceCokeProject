package com.shop.service.impl;

import com.shop.dto.OrderDetailDTO;
import com.shop.mapper.OrderDetailMapper;
import com.shop.pojo.OrderDetail;
import com.shop.service.OrderDetailService;
import com.shop.utils.ConstantUtil;
import com.shop.utils.RegexUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Override
    public List<OrderDetail> selectOrderDetailList(String orderId) {
        if (RegexUtil.isDigital(orderId))
            return orderDetailMapper.selectOrderDetailList(Integer.valueOf(orderId));
        return null;
    }

    @Override
    public OrderDetail selectOrderDetailById(String id) {
        return orderDetailMapper.selectOrderDetailById(Integer.valueOf(id));
    }

    @Override
    public int insertOrderDetail(List<OrderDetailDTO> orderDetailDTOs) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        Date date = new Date();
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOs) {
            OrderDetail orderDetail = transformOrderDetail(orderDetailDTO);
            orderDetail.setCrateTime(date);
            orderDetails.add(orderDetail);
        }
        return orderDetailMapper.insertOrderDetail(orderDetails);
    }

    @Override
    public int updateOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = transformOrderDetail(orderDetailDTO);
        return orderDetailMapper.updateOrderDetail(orderDetail);
    }

    @Override
    public int deleteOrderDetailByOrderId(String orderId) {
        if (RegexUtil.isDigital(orderId))
            return orderDetailMapper.deleteOrderDetailByOrderId(Integer.valueOf(orderId));
        return ConstantUtil.FAILED;
    }

    private OrderDetail transformOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(orderDetailDTO.getId());
        orderDetail.setNum(orderDetailDTO.getNum());
        orderDetail.setOrderId(orderDetailDTO.getOrderId());
        orderDetail.setPrice(orderDetailDTO.getPrice());
        orderDetail.setProductImg(orderDetailDTO.getProductImg());
        orderDetail.setProductName(orderDetailDTO.getProductName());
        orderDetail.setStatus(orderDetailDTO.getStatus());
        orderDetail.setTotalPrice(orderDetailDTO.getTotalPrice());
        orderDetail.setUpdateTime(new Date());
        return orderDetail;
    }
}
