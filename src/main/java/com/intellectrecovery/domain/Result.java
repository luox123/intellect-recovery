package com.intellectrecovery.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private long code;
    private String message;
    private Object data;

    /**
     * 请求成功
     * @param message
     * @return
     */
    public static Result success(String message) {
        return new Result(200, message, null);
    }

    /**
     * 请求成功
     * @param message
     * @param data 响应数据
     * @return
     */
    public static Result success(String message, Object data) {
        return new Result(200, message, data);
    }

    /**
     * 请求失败
     * @param message
     * @return
     */
    public static Result fail(String message) {
        return new Result(500, message, null);
    }

    /**
     * 请求成功
     * @param message
     * @param data 响应数据
     * @return
     */
    public static Result fail(String message, Object data) {
        return new Result(500, message, data);
    }
}
