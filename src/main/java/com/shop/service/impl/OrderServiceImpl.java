package com.shop.service.impl;

import com.shop.dto.OrderDTO;
import com.shop.mapper.OrderMapper;
import com.shop.pojo.Order;
import com.shop.service.OrderService;
import com.shop.utils.ConstantUtil;
import com.shop.utils.RegexUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Override
    public List<Order> selectOrderList(OrderDTO orderDTO) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", orderDTO.getId());
        map.put("userId", orderDTO.getUserId());
        map.put("name", orderDTO.getName());
        map.put("orderNum", orderDTO.getOrderNum());
        map.put("phone", orderDTO.getPhone());
        map.put("orderStatus", orderDTO.getOrderStatus());
        map.put("payType", orderDTO.getPayType());
        map.put("pageIndex", orderDTO.getPageIndex());
        map.put("pageSize", orderDTO.getPageSize());
        return orderMapper.selectOrderList(map);
    }

    @Override
    public Order selectOrderById(String id) {
        if (RegexUtil.isDigital(id))
            return orderMapper.selectOrderById(Integer.valueOf(id));
        return null;
    }

    @Override
    public int insertOrder(OrderDTO orderDTO) {
        Order order = transformOrder(orderDTO);
        Date date = new Date();
        order.setCreateTime(date);
        order.setPayTime(date);
        return orderMapper.insertOrder(order);
    }

    @Override
    public int updateOrder(OrderDTO orderDTO) {
        Order order = transformOrder(orderDTO);
        order.setUpdateTime(new Date());
        return orderMapper.updateOrder(order);
    }

    @Override
    public int deleteOrder(String id) {
        if (RegexUtil.isDigital(id))
            return orderMapper.deleteOrder(Integer.valueOf(id));
        return ConstantUtil.FAILED;
    }

    @Override
    public int orderAmount() {
        return orderMapper.orderAmount();
    }

    public Order transformOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setUserId(orderDTO.getUserId());
        order.setAddress(orderDTO.getAddress());
        order.setPhone(orderDTO.getPhone());
        order.setName(orderDTO.getName());
        order.setRemark(orderDTO.getRemark());
        order.setFreight(orderDTO.getFreight());
        order.setOrderStatus(orderDTO.getOrderStatus());
        order.setPayMoney(orderDTO.getPayMoney());
        order.setPayType(orderDTO.getPayType());
        order.setSuccessTime(orderDTO.getSuccessTime());
        order.setSendTime(orderDTO.getSendTime());
        order.setUpdateTime(orderDTO.getUpdateTime());
        order.setPayTime(orderDTO.getPayTime());
        return order;
    }
}
