package com.zed.common.validate;


import com.zed.common.constant.StatusCode;
import lombok.extern.slf4j.Slf4j;

/**
 * 校验工具状态码
 *
 * @author zed
 */
@Slf4j
public class ValidateTypeUtils {

    /**
     * 将校验字段的注解类型对应上状态码
     *
     * @param validateType
     * @return
     */
    public static Integer validateType(String validateType) {
        Integer code;
        switch (validateType) {
            case "Null":
                code = StatusCode.VERIFY_410004.getValue();
                break;
            case "NotNull":
            case "NotBlank":
            case "NotEmpty":
                code = StatusCode.VERIFY_410003.getValue();
                break;
            case "AssertTrue":
            case "AssertFalse":
                code = StatusCode.VERIFY_410009.getValue();
                break;
            case "Min":
            case "Max":
            case "DecimalMin":
            case "DecimalMax":
            case "Size":
            case "Digits":
            case "Range":
                code = StatusCode.VERIFY_410007.getValue();
                break;
            case "Past":
            case "Future":
                code = StatusCode.VERIFY_410010.getValue();
                break;
            case "Pattern":
                code = StatusCode.VERIFY_410005.getValue();
                break;
            case "Email":
                code = StatusCode.VERIFY_410011.getValue();
                break;
            case "Length":
                code = StatusCode.VERIFY_410006.getValue();
                break;
            default:
                code = StatusCode.VERIFY_410000.getValue();
        }
//        log.info( "校验信息是:" + validateType + "返回码是:" + code );
        return code;
    }


}
