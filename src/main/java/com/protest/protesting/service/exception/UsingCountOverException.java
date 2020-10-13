package com.protest.protesting.service.exception;

import com.protest.protesting.exception.BusinessException;
import com.protest.protesting.exception.ErrorCode;

public class UsingCountOverException extends BusinessException {
    public UsingCountOverException() {
        super(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
