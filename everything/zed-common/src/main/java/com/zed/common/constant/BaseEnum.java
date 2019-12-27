package com.zed.common.constant;


/**
 * BaseEnum（特殊枚举需要实现）
 *
 * @author zed
 * @description 枚举不允许extends抽象类, 所以是implements接口
 * 目的：
 * 是需要将枚举设值到异常类中的省略写法
 * <p>
 * 例如：throw new VerifyException(AdminEnum.USER_NAME_REPEAT);
 * </p>
 * 而异常类需要code,message等字段,否则需要
 * <p>
 * 例如：throw new VerifyException(AdminEnum.USER_NAME_REPEAT.getValue(),AdminEnum.USER_NAME_REPEAT.getMessage());
 * </p>
 * 所以需要实现接口并在异常类中用泛型接收
 * <p>
 * 例如：
 * public <T extends BaseEnum> VerifyException(T t) {
 * super(t.getMessage());
 * this.code = t.getValue();
 * }
 * </p>
 * @date 2019/12/16 20:24
 */
public interface BaseEnum {

    /**
     * 获取枚举value
     *
     * @return
     */
    Integer getValue();

    /**
     * 获取枚举message
     *
     * @return
     */
    String getMessage();


}
