package com.shop.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result implements Serializable {
    private Integer code;
    private String msg;
    private Object data;

    private Result() {}

    private Result(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    private Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result create(Integer code, String msg) {
        return new Result(code, msg);
    }

    public static Result create(Integer code, String msg, Object data) {
        return new Result(code, msg, data);
    }

}

