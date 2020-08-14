package com.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.shop.dto.OrderDTO;
import com.shop.pojo.Order;
import com.shop.service.OrderService;
import com.shop.utils.HttpCode;
import com.shop.utils.Message;
import com.shop.utils.Result;
import com.shop.vo.OrderVO;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("/list")
    public Result orderList(OrderDTO orderDTO) {
        List<OrderVO> orderVOs = orderService.selectOrderList(orderDTO);
        if (orderVOs != null) {
            JSONObject json = new JSONObject();
            json.put("orderList", orderVOs);
            json.put("orderAmount", orderService.orderAmount(orderDTO));
            return Result.create(HttpCode.OK, Message.SELECT_SUCCESS, json);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.SELECT_FAILED);
    }

    @PostMapping("/add")
    public Result addOrder(OrderDTO orderDTO) {
        if (orderService.insertOrder(orderDTO) == 1) {
            return Result.create(HttpCode.OK, Message.ADD_SUCCESS);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.ADD_FAILED);
    }

    @PostMapping("/update")
    public Result updateOrder(OrderDTO orderDTO) {
        if (orderService.updateOrder(orderDTO) == 1) {
            return Result.create(HttpCode.OK, Message.UPDATE_SUCCESS);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.UPDATE_FAILED);
    }

    @PostMapping("/del")
    public Result delOrder(String id) {
        if (orderService.deleteOrder(id) == 1) {
            return Result.create(HttpCode.OK, Message.DEL_SUCCESS);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.DEL_FAILED);
    }

}
