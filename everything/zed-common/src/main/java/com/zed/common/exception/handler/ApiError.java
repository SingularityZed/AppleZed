package com.zed.common.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zed.common.utils.DateUtils;
import lombok.Data;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * API异常
 *
 * @author zed
 * @date 2019-12-16
 */
@Data
class ApiError {

    private Integer status = BAD_REQUEST.value();

    @JsonFormat(pattern = DateUtils.TIME_FORMAT_PATTERN)
    private LocalDateTime timestamp;

    private String message;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    static ApiError error(String message) {
        ApiError apiError = new ApiError();
        apiError.setMessage(message);
        return apiError;
    }

    static ApiError error(Integer status, String message) {
        ApiError apiError = new ApiError();
        apiError.setStatus(status);
        apiError.setMessage(message);
        return apiError;
    }
}


