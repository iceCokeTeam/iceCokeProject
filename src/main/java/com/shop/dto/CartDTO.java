package com.shop.dto;

import lombok.Data;

@Data
public class CartDTO {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer quantity;
}
