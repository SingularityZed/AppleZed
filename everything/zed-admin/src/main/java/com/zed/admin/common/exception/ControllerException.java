package com.zed.admin.common.exception;

import com.zed.admin.common.constant.BaseEnum;
import com.zed.admin.common.constant.StatusCode;
import lombok.Getter;

/**
 * ControllerException
 *
 * @author zed
 * @date 2019/12/16 14:36
 */
@Getter
public class ControllerException extends RuntimeException {

    private Integer code = StatusCode.SERVER_510000.value();

    private Exception e;

    public ControllerException(String msg){ super(msg);}
    public ControllerException(String msg,Exception e){
        super(msg);
        this.e = e;
    }

    public ControllerException(Integer code,String msg){
        super(msg);
        this.code = code;
    }

    public ControllerException(Integer code,String msg,Exception e){
        super(msg);
        this.code = code;
        this.e = e;
    }


    public <T extends BaseEnum> ControllerException(T t) {
        super(t.getMessage());
        this.code = t.getValue();
    }

}