package com.zed.admin.common.exception.handler;

import com.zed.admin.common.constant.StatusCode;
import com.zed.admin.common.exception.ControllerException;
import com.zed.admin.common.exception.DaoException;
import com.zed.admin.common.exception.ManagerException;
import com.zed.admin.common.exception.ServiceException;
import com.zed.admin.common.utils.ThrowableUtil;
import com.zed.admin.common.validate.ValidateTypeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

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
    public ResponseEntity handleException(Throwable e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error(e.getMessage()));
    }

    /**
     * validate 插件异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        // 得到校验字段信息
        String[] str = Objects.requireNonNull(e.getBindingResult().getAllErrors().get(0).getCodes())[1].split("\\.");
        // 数据校验信息
        // 校验字段的注解类型
        String validateType = str[0];
        // 根据类型得到code
        Integer code = ValidateTypeUtils.validateType(validateType);
        // 返回校验信息
        String message = str[1] + ":" + e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return buildResponseEntity(ApiError.error(code, message));
    }

    /**
     * 接口无权访问异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleAccessDeniedException(AccessDeniedException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error(FORBIDDEN.value(), e.getMessage()));
    }

    /**
     * 类型转换错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error(StatusCode.CLIENT_400009.getValue(), e.getMessage()));
    }

    /**
     * 参数不能为空异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity handleMissingServletRequestPartException(MissingServletRequestPartException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error(StatusCode.VERIFY_410003.getValue(), e.getMessage()));
    }

    /**
     * 请求类型异常 405
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error(METHOD_NOT_ALLOWED.value(), e.getMessage()));
    }

    /**
     * 媒体不支持异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public ResponseEntity handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error(UNSUPPORTED_MEDIA_TYPE.value(), e.getMessage()));
    }

    /**
     * sql异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler({SQLException.class})
    public ResponseEntity handleSQLException(SQLException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error(StatusCode.SERVER_540000.getValue(), e.getMessage()));
    }

    /**
     * JSON转换异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handlerHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error(StatusCode.CLIENT_400010.getValue(), e.getMessage()));
    }

    /**
     * 文件大小超出系统限制大小
     *
     * @param e
     * @return
     */
    @ExceptionHandler(FileUploadBase.FileSizeLimitExceededException.class)
    public ResponseEntity fileUploadBase$FileSizeLimitExceededException(FileUploadBase.FileSizeLimitExceededException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error(StatusCode.CLIENT_400011.getValue(), e.getMessage()));
    }

    /**
     * 非法参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity handlerIllegalArgumentException(IllegalArgumentException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error(StatusCode.VERIFY_410008.getValue(), e.getMessage()));
    }

    /**
     * 展现层异常
     *
     * @param e 具体异常
     * @return
     */
    @ExceptionHandler(ControllerException.class)
    public ResponseEntity controllerException(ControllerException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error(StatusCode.SERVER_510000.getValue(), e.getMessage()));
    }

    /**
     * 数据处理异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ManagerException.class)
    public ResponseEntity managerException(ManagerException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error(StatusCode.SERVER_520000.getValue(), e.getMessage()));
    }

    /**
     * 业务逻辑异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity serviceException(ServiceException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error(StatusCode.SERVER_530000.getValue(), e.getMessage()));
    }

    /**
     * 数据库异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(DaoException.class)
    public ResponseEntity daoException(DaoException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error(StatusCode.SERVER_540000.getValue(), e.getMessage()));
    }

    /**
     * 统一返回
     *
     * @param apiError
     * @return
     */
    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }
}
