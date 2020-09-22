package com.protest.protesting.utils;

import com.protest.protesting.entity.ApiResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MainUtils {
    @Autowired HttpServletRequest httpServletRequest;

    public String getReqURI() {
        return httpServletRequest.getRequestURI();
    }

    public ApiResponseEntity successResponse(Object result) {
        return new ApiResponseEntity(
                HttpStatus.OK.value(),
                "",
                result,
                this.getReqURI()
        );
    }

    public ApiResponseEntity badResponse(Object result) {
        return new ApiResponseEntity(
                HttpStatus.BAD_REQUEST.value(),
                "",
                result,
                this.getReqURI()
        );
    }
}
