package com.protest.protesting.exception;

import org.springframework.dao.DataAccessException;

/**
 * 커스텀 형태로 String Format Exception 낼떄 사용
 */
public class CustomException extends DataAccessException {
    public CustomException(String msg) {
        super(msg);
    }
}
