package com.zed.common.exception.handler;


import com.zed.common.constant.StatusCode;
import com.zed.common.exception.*;
import com.zed.common.utils.ThrowableUtil;
import com.zed.common.validate.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.Objects;

/**
 * 全局异常处理类
 *
 * @author zed
 * @date 2019-12-16
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理未知异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public ResponseResult handleException(Throwable e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return ResponseResult.failed(StatusCode.SERVER_500000.getValue(), e.getMessage());
    }


    /**
     * validate 插件异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        // 得到校验字段信息
        String[] str = Objects.requireNonNull(e.getBindingResult().getAllErrors().get(0).getCodes())[1].split("\\.");
        // 数据校验信息
        // 校验字段的注解类型
        String validateType = str[0];
        // 根据类型得到code
        Integer code = ValidationUtils.validateType(validateType);
        // 返回校验信息
        String message = str[1] + ":" + e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseResult.failed(code, message);
    }

    /**
     * 接口无权访问异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseResult handleAccessDeniedException(AccessDeniedException e) {
        return dealResponseResult(StatusCode.CLIENT_400003.getValue(), e);
    }

    /**
     * 类型转换错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseResult handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return dealResponseResult(StatusCode.CLIENT_400009.getValue(), e);
    }

    /**
     * 参数不能为空异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseResult handleMissingServletRequestPartException(MissingServletRequestPartException e) {
        return dealResponseResult(StatusCode.VERIFY_410003.getValue(), e);
    }

    /**
     * 请求类型异常 405
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return dealResponseResult(StatusCode.CLIENT_400007.getValue(), e);
    }

    /**
     * 媒体不支持异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public ResponseResult handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return dealResponseResult(StatusCode.CLIENT_400008.getValue(), e);
    }

    /**
     * sql异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler({SQLException.class})
    public ResponseResult handleSqlException(SQLException e) {
        return dealResponseResult(StatusCode.SERVER_540000.getValue(), e);
    }

    /**
     * sql异常处理字段
     *
     * @param e
     * @return
     */
    @ExceptionHandler({SQLSyntaxErrorException.class})
    public ResponseResult handleSqlSyntaxErrorException(SQLSyntaxErrorException e) {
        return dealResponseResult(StatusCode.SERVER_540000.getValue(), e);
    }

    /**
     * JSON转换异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseResult handlerHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return dealResponseResult(StatusCode.CLIENT_400010.getValue(), e);
    }

    /**
     * 文件大小超出系统限制大小
     *
     * @param e
     * @return
     */
    @ExceptionHandler(FileUploadBase.FileSizeLimitExceededException.class)
    public ResponseResult fileUploadBaseFileSizeLimitExceededException(FileUploadBase.FileSizeLimitExceededException e) {
        return dealResponseResult(StatusCode.CLIENT_400011.getValue(), e);
    }

    /**
     * 非法参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseResult handlerIllegalArgumentException(IllegalArgumentException e) {
        return dealResponseResult(StatusCode.VERIFY_410008.getValue(), e);
    }

    /**
     * 校验逻辑异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(VerifyException.class)
    public ResponseResult verifyException(VerifyException e) {
        return dealResponseResult(e.getCode(), e);
    }


    /**
     * 展现层异常
     *
     * @param e 具体异常
     * @return
     */
    @ExceptionHandler(ControllerException.class)
    public ResponseResult controllerException(ControllerException e) {
        return dealResponseResult(e.getCode(), e);
    }

    /**
     * 数据处理异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ManagerException.class)
    public ResponseResult managerException(ManagerException e) {
        return dealResponseResult(e.getCode(), e);
    }

    /**
     * 业务逻辑异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseResult serviceException(ServiceException e) {
        return dealResponseResult(e.getCode(), e);
    }

    /**
     * 数据库异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(DaoException.class)
    public ResponseResult daoException(DaoException e) {
        return dealResponseResult(e.getCode(), e);
    }

    /**
     * 统一返回
     *
     * @return
     */
    private <E extends Exception> ResponseResult dealResponseResult(Integer code, E e) {
        // 异常码
        log.error("业务异常,code:" + code);
        // 打印堆栈信息
        log.error("response_result:{}", ThrowableUtil.getStackTrace(e));
        return ResponseResult.failed(code, e.getMessage());
    }
}
