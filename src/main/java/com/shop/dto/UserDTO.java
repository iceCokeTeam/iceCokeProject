package com.shop.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String userName;
    private Integer status;
    private Integer pageIndex;
    private Integer pageSize;
}
