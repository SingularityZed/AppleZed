package com.zed.admin.common.exception;

import com.zed.admin.common.constant.BaseEnum;
import com.zed.admin.common.constant.StatusCode;
import lombok.Getter;

/**
 * VerifyException
 *
 * @author zed
 * @date 2019/12/16 14:36
 */
@Getter
public class VerifyException extends RuntimeException {

    private Integer code = StatusCode.VERIFY_410000.value();

    private Exception e;

    public VerifyException(String msg) {
        super(msg);
    }

    public VerifyException(String msg, Exception e) {
        super(msg);
        this.e = e;
    }

    public VerifyException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public VerifyException(Integer code, String msg, Exception e) {
        super(msg);
        this.code = code;
        this.e = e;
    }

    public <T extends BaseEnum> VerifyException(T t) {
        super(t.getMessage());
        this.code = t.getValue();
    }

}
