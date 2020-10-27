package com.mini.app.common.exception;

import com.mini.app.common.entity.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName: ExceptionHandle
 * @Description: 统一异常处理
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandle {

    /**
     * @param e
     * @return
     * @Description 处理业务类异常
     */
    @ExceptionHandler(value = {ApiException.class})
    public ApiResponse<String> HandleApiException(ApiException e) {
        log.error("发生业务异常ApiException:", e);
        return ApiResponse.createApiResponse(e.getMessage(), e.getResult());
    }
}
