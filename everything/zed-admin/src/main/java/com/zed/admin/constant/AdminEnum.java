package com.zed.admin.constant;


import com.zed.common.constant.BaseEnum;

/**
 * zed-admin枚举类
 *
 * @author zed
 * @date 2019/3/25 15:23
 **/
public enum AdminEnum implements BaseEnum {

    /**
     * 用户名称重复
     */
    USER_NAME_REPEAT(10001, "用户名称重复"),

    /**
     * 用户联系电话重复
     */
    USER_TELEPHONE_REPEAT(10002, "用户联系电话重复");


    private final Integer code;

    private final String message;

    AdminEnum(Integer code, String message) {
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
