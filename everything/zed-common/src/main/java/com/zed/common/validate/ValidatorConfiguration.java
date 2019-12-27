package com.zed.common.validate;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * 验证模式配置类
 * <p>
 * 1、普通模式（默认是这个模式）
 * 　　普通模式(会校验完所有的属性，然后返回所有的验证失败信息)
 * 2、快速失败返回模式
 * 　　快速失败返回模式(只要有一个验证失败，则返回)
 * .failFast( true ) //.addProperty( "hibernate.validator.fail_fast", "true" )
 * failFast：true  快速失败返回模式    false 普通模式
 * （hibernate.validator.fail_fast：true  快速失败返回模式    false 普通模式）
 * </p>
 *
 * @author zed
 */
@Configuration
public class ValidatorConfiguration {
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                //.addProperty( "hibernate.validator.fail_fast", "false" )
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return validator;
    }


    /**
     * 处理自定义校验结果方法
     * 在Controller的@RestController
     * 下面添加@Validated注解，
     * 并且在请求体除去GET请求外加上返回result
     * (@RequestBody @Valid DemoModel demo, BindingResult result)
     * 在GlobalExceptionHandler中添加这个方法
     * 就可以在这个方法下获取校验结果
     *
     * @param ex
     * @return
     */
/*  @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public RestResponse handleApiConstraintViolationException(ConstraintViolationException ex) {
        StringBuffer ms = new StringBuffer();
        Integer code = StatusCode.VALIDATED_700000.value();//数据校验异常
        //得到异常信息
        Set <ConstraintViolation <?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation <?> violation : violations) {
            ms.append( violation.getMessage() );
            //放code，前端根据code来提示反馈信息
            //得到注解类型
            String validateTypeTemp = violation.getConstraintDescriptor().getAnnotation().annotationType().getTypeName();
            if (StringUtils.isNotBlank( validateTypeTemp )) {
                String validateType = validateTypeTemp.substring( validateTypeTemp.lastIndexOf( "." ) + 1 );
                code = ValidateTypeUtils.validateType( validateType );
            }
        }
        return RestResponse.failure( "数据校验信息:" + ms.toString(), code );
    }*/

/*  @RequestMapping("/demo")
    public void demo2(@RequestBody @Valid DemoModel demo, BindingResult result){
        if(result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
        }
    }*/
}
