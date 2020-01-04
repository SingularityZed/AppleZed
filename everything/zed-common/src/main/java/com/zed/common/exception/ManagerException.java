package com.zed.common.exception;


import cn.hutool.core.util.StrUtil;
import com.zed.common.constant.BaseEnum;
import com.zed.common.constant.StatusCode;
import lombok.Getter;

/**
 * ManagerException
 *
 * @author zed
 *@date 2019/12/16 14:36
 **/
@Getter
public class ManagerException extends RuntimeException {

    private Integer code= StatusCode.SERVER_520000.getValue();

    private Exception e;

    public ManagerException(String msg) {
        super(msg);
    }

    public ManagerException(String msg, Exception e) {
        super(msg);
        this.e = e;
    }

    public ManagerException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public ManagerException(Integer code, String msg, Exception e) {
        super(msg);
        this.code = code;
        this.e = e;
    }
    public ManagerException(Integer code, String msg, String extraMag) {
        super(msg + StrUtil.COLON + extraMag);
        this.code = code;
    }


    public <T extends BaseEnum> ManagerException(T t) {
        super(t.getMessage());
        this.code = t.getValue();
    }
}
