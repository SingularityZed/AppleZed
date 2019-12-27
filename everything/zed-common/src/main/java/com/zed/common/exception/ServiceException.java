package com.zed.common.exception;


import com.zed.common.constant.BaseEnum;
import com.zed.common.constant.StatusCode;
import lombok.Getter;

/**
 * ServiceException
 *
 * @author zed
 * @date 2019/3/27 15:30
 **/
@Getter
public class ServiceException extends RuntimeException {

    private Integer code = StatusCode.SERVER_530000.getValue();

    private Exception e;

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Exception e) {
        super(msg);
        this.e = e;
    }

    public ServiceException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public ServiceException(Integer code, String msg, Exception e) {
        super(msg);
        this.code = code;
        this.e = e;
    }

    public <T extends BaseEnum> ServiceException(T t) {
        super(t.getMessage());
        this.code = t.getValue();
    }
}
