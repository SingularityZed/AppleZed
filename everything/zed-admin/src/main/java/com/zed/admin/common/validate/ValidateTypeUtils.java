package com.zed.admin.common.validate;


import com.zed.admin.common.constant.StatusCode;
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
                code = StatusCode.VERIFY_410004.value();
                break;
            case "NotNull":
            case "NotBlank":
            case "NotEmpty":
                code = StatusCode.VERIFY_410003.value();
                break;
            case "AssertTrue":
            case "AssertFalse":
                code = StatusCode.VERIFY_410009.value();
                break;
            case "Min":
            case "Max":
            case "DecimalMin":
            case "DecimalMax":
            case "Size":
            case "Digits":
            case "Range":
                code = StatusCode.VERIFY_410007.value();
                break;
            case "Past":
            case "Future":
                code = StatusCode.VERIFY_410010.value();
                break;
            case "Pattern":
                code = StatusCode.VERIFY_410005.value();
                break;
            case "Email":
                code = StatusCode.VERIFY_410011.value();
                break;
            case "Length":
                code = StatusCode.VERIFY_410006.value();
                break;
            default:
                code = StatusCode.VERIFY_410000.value();
        }
//        log.info( "校验信息是:" + validateType + "返回码是:" + code );
        return code;
    }


}
