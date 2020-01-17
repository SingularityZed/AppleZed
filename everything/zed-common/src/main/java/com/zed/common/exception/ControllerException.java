package com.zed.common.exception;

import cn.hutool.core.util.StrUtil;
import com.zed.common.constant.BaseEnum;
import com.zed.common.constant.StatusCode;
import lombok.Getter;

/**
 * ControllerException
 *
 * @author zed
 * @date 2019/12/16 14:36
 */
@Getter
public class ControllerException extends RuntimeException {

    private Integer code = StatusCode.SERVER_510000.getValue();

    private Exception e;

    public ControllerException(String msg){ super(msg);}
    public ControllerException(String msg,Exception e){
        super(msg);
        this.e = e;
    }
    /**
     * 通过code查缓存的数据结果
     *
     * @param code
     */
    public ControllerException(Integer code) {
        super(code.toString());
        this.code = code;
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
    public ControllerException(Integer code, String msg, String extraMag) {
        super(msg + StrUtil.COLON + extraMag);
        this.code = code;
    }

    public <T extends BaseEnum> ControllerException(T t) {
        super(t.getMessage());
        this.code = t.getValue();
    }

}