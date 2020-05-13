package com.zed.service.dto;

import lombok.Data;


/**
 * 枚举类
 *
 * @author zed
 * @date 2020-01-16
 */
@Data
public enum UserVO implements BaseEnum {
    /**
     * xxx
     */
    XXXX(20001, "KKKK"),

    /**
     * xxx
     */
    YYYY(20002, "CCCC");


    private final Integer code;

    private final String message;

    UserEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getValue() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}