package com.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.shop.dto.OrderDetailDTO;
import com.shop.pojo.OrderDetail;
import com.shop.service.OrderDetailService;
import com.shop.utils.HttpCode;
import com.shop.utils.Message;
import com.shop.utils.Result;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {
    @Resource
    private OrderDetailService orderDetailService;

    @PostMapping("/list")
    public Result orderDetailList(String orderId) {
        List<OrderDetail> orderDetails = orderDetailService.selectOrderDetailList(orderId);
        JSONObject json = new JSONObject();
        if (orderDetails != null) {
            json.put("orderDetailList", orderDetails);
            return Result.create(HttpCode.OK, Message.SELECT_SUCCESS, json);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.SELECT_FAILED);
    }

    @PostMapping("/add")
    public Result addOrderDetail(List<OrderDetailDTO> orderDetailDTOs) {
        if (orderDetailService.insertOrderDetail(orderDetailDTOs) > 0) {
            return Result.create(HttpCode.OK, Message.ADD_SUCCESS);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.ADD_FAILED);
    }

    @PostMapping("/update")
    public Result updateOrderDetail(OrderDetailDTO orderDetailDTO) {
        if (orderDetailService.updateOrderDetail(orderDetailDTO) == 1) {
            return Result.create(HttpCode.OK, Message.UPDATE_SUCCESS);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.UPDATE_FAILED);
    }

    @PostMapping("/del")
    public Result delOrderDetail(String orderId) {
        if (orderDetailService.deleteOrderDetailByOrderId(orderId) > 0) {
            return Result.create(HttpCode.OK, Message.DEL_SUCCESS);
        }
        return Result.create(HttpCode.BAD_REQUEST, Message.DEL_FAILED);
    }
}
