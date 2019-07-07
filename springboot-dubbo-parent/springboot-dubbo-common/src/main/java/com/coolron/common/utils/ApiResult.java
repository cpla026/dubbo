package com.coolron.common.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResult {

    // 响应业务状态
    private Integer status;
    // 响应消息
    private String msg;
    // 响应中的数据
    private Object data;

    public static ApiResult build(Integer status, String msg, Object data) {
        return new ApiResult(status, msg, data);
    }

    public static ApiResult ok(Object data) {
        return new ApiResult(data);
    }

    public static ApiResult ok() {
        return new ApiResult(null);
    }

    public ApiResult() { }

    public static ApiResult build(Integer status, String msg) {
        return new ApiResult(status, msg, null);
    }

    public ApiResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ApiResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }
}
