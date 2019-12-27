package com.zed.common.constant;

/**
 * @author zed
 * @date 2019/3/25 15:23
 **/
public enum StatusCode implements BaseEnum{

    /**
     * 成功
     */
    RESULT_OK(200, "请求成功"),

    /**
     * 客户端
     */
    CLIENT_400001(400001, "TOKEN不存在"),
    CLIENT_400002(400002, "TOKEN过期"),
    CLIENT_400003(400003, "被禁止访问"),
    CLIENT_400004(400004, "根记录不可操作"),
    CLIENT_400005(400005, "等级超过规定层级"),
    CLIENT_400006(400006, "请求地址不存在"),
    CLIENT_400007(400007, "请求方法不允许"),
    CLIENT_400008(400008, "无法处理请求附带的媒体格式"),
    CLIENT_400009(400009, "参数类型错误"),
    CLIENT_400010(400010, "JSON转换异常"),
    CLIENT_400011(400011, "上传文件过大"),

    /**
     * 客户端验证
     */
    VERIFY_410000(410000, "参数校验异常"),
    VERIFY_410001(410001, "操作数据不存在"),
    VERIFY_410002(410002, "操作数据已存在"),
    VERIFY_410003(410003, "字段不能为空"),
    VERIFY_410004(410004, "字段应该为空"),
    VERIFY_410005(410005, "正则校验错误"),
    VERIFY_410006(410006, "超过最大长度"),
    VERIFY_410007(410007, "数据应在正确范围"),
    VERIFY_410008(410008, "非法参数"),
    VERIFY_410009(410009, "断言错误"),
    VERIFY_410010(410010, "日期错误"),
    VERIFY_410011(410011, "邮箱错误"),

    /**
     * 服务端
     */
    SERVER_500000(500000, "未知异常"),
    SERVER_510000(510000, "展现层异常"),
    SERVER_520000(520000, "管理层异常"),
    SERVER_530000(530000, "业务层异常"),
    SERVER_540000(540000, "数据库异常");

    private final Integer code;

    private final String message;

    StatusCode(Integer code, String message) {
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
