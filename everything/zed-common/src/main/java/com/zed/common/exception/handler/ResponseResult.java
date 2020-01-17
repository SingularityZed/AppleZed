package com.zed.common.exception.handler;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.zed.common.constant.StatusCode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通用响应对象
 * <p>
 * ConcurrentHashMap不支持null键和null值
 * </p>
 *
 * @author zed
 * @date 2020/1/17 12:15
 */
public class ResponseResult<T> extends ConcurrentHashMap<String, Object> {

    private final static ResponseResult RESPONSE_RESULT = new ResponseResult();

    private final static String SUCCESS = "success";
    private final static String CODE = "code";
    private final static String MESSAGE = "message";
    private final static String DATA = "data";
    private final static String SERVER_TIME = "serverTime";
    private final static String TIMESTAMP = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    public static ResponseResult succeed() {
        RESPONSE_RESULT.setCode(StatusCode.RESULT_OK.getValue());
        RESPONSE_RESULT.setMessage(SUCCESS);
        RESPONSE_RESULT.setServerTime();
        return RESPONSE_RESULT;
    }

    public static ResponseResult succeed(String message) {
        RESPONSE_RESULT.setCode(StatusCode.RESULT_OK.getValue());
        RESPONSE_RESULT.setMessage(message);
        RESPONSE_RESULT.setServerTime();
        return RESPONSE_RESULT;
    }

    public static <T> ResponseResult succeed(T data) {
        RESPONSE_RESULT.setCode(StatusCode.RESULT_OK.getValue());
        RESPONSE_RESULT.setMessage(SUCCESS);
        RESPONSE_RESULT.setServerTime();
        RESPONSE_RESULT.setData(data);
        return RESPONSE_RESULT;
    }

    public static <T> ResponseResult succeed(T data, String message) {
        RESPONSE_RESULT.setCode(StatusCode.RESULT_OK.getValue());
        RESPONSE_RESULT.setMessage(message);
        RESPONSE_RESULT.setServerTime();
        RESPONSE_RESULT.setData(data);
        return RESPONSE_RESULT;
    }

    public static ResponseResult failed(Integer code) {
        RESPONSE_RESULT.setCode(code);
        RESPONSE_RESULT.setServerTime();
        // 参数字典 缓存获取
        RESPONSE_RESULT.setMessage(code.toString());
        return RESPONSE_RESULT;
    }

    /**
     * 不建议使用直接消息,消息修改不好扩展
     */
    public static ResponseResult failed(Integer code, String message) {
        RESPONSE_RESULT.setCode(code);
        RESPONSE_RESULT.setServerTime();
        RESPONSE_RESULT.setMessage(message);
        return RESPONSE_RESULT;
    }

    public static ResponseResult failedWith(Integer code, String appendMsg) {
        RESPONSE_RESULT.setCode(code);
        RESPONSE_RESULT.setServerTime();
        // 参数字典 缓存获取
        RESPONSE_RESULT.setMessage(code.toString() + StringPool.COLON + appendMsg);
        return RESPONSE_RESULT;
    }

    public void setMessage(String message) {
        if (message != null) {
            put(MESSAGE, message);
        }
    }

    public void setCode(Integer code) {
        if (code != null) {
            put(CODE, code);
        }
    }

    public void setData(Object data) {
        if (data != null) {
            put(DATA, data);
        }
    }

    public void setServerTime() {
        put(SERVER_TIME, TIMESTAMP);
    }

    public Integer getCode() {
        return (Integer) this.get(CODE);
    }


}
