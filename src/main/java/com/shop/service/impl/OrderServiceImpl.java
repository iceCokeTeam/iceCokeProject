package com.shop.service.impl;

import com.shop.dto.OrderDTO;
import com.shop.mapper.OrderDetailMapper;
import com.shop.mapper.OrderMapper;
import com.shop.pojo.Order;
import com.shop.service.OrderService;
import com.shop.utils.ConstantUtil;
import com.shop.utils.OrderNumUtil;
import com.shop.utils.RegexUtil;
import com.shop.utils.TimeUtil;
import com.shop.vo.OrderVO;
import com.shop.vo.UserVO;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.core.annotation.OrderUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Override
    public List<OrderVO> selectOrderList(OrderDTO orderDTO) {
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
        List<Order> orders = orderMapper.selectOrderList(map);
        List<OrderVO> orderVOs = new ArrayList<>();
        for (Order order : orders) {
            orderVOs.add(transformOrderVO(order));
        }
        return orderVOs;
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
        order.setOrderNum(OrderNumUtil.GetRandom());
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
        if (RegexUtil.isDigital(id)){
            orderDetailMapper.deleteOrderDetailByOrderId(Integer.valueOf(id));
            return orderMapper.deleteOrder(Integer.valueOf(id));
        }
        return ConstantUtil.FAILED;
    }

    @Override
    public int orderAmount(OrderDTO orderDTO) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderNum", orderDTO.getOrderNum());
        return orderMapper.orderAmount(map);
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

    public OrderVO transformOrderVO(Order order) {
        OrderVO orderVO = new OrderVO();
        orderVO.setId(order.getId());
        orderVO.setOrderNum(order.getOrderNum());
        orderVO.setAddress(order.getAddress());
        orderVO.setPhone(order.getPhone());
        orderVO.setName(order.getName());
        orderVO.setRemark(order.getRemark());
        orderVO.setFreight(order.getFreight());
        orderVO.setPayTime(TimeUtil.dateFormat(order.getPayTime()));
        orderVO.setOrderStatus(order.getOrderStatus());
        orderVO.setPayMoney(order.getPayMoney());
        orderVO.setPayType(order.getPayType());
        orderVO.setSuccessTime(TimeUtil.dateFormat(order.getSuccessTime()));
        orderVO.setSendTime(TimeUtil.dateFormat(order.getSendTime()));
        orderVO.setCreateTime(TimeUtil.dateFormat(order.getCreateTime()));
        orderVO.setUpdateTime(TimeUtil.dateFormat(order.getUpdateTime()));
        orderVO.setOrderDetail(order.getOrderDetail());
        System.out.println(order.getUser());
        UserServiceImpl userService = new UserServiceImpl();
        orderVO.setUserVO(userService.transformUserVO(order.getUser()));
        return orderVO;
    }
}
