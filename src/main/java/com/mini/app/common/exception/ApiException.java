package com.mini.app.common.exception;

import com.mini.app.common.enums.Result;
import lombok.Getter;

/**
 * @ClassName: ApiException
 * @Description: API业务异常
 */

@Getter
public class ApiException extends RuntimeException {
    private Result result;

    public ApiException(Result result) {
        super(result.getMsg());
        this.result = result;
    }

}
