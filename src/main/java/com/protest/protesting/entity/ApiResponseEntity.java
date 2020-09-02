package com.protest.protesting.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.json.simple.JSONObject;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ApiResponseEntity {
    private Date timestamp;
    private int status;
    private String error;

//    기존
//    private JSONObject message;

//    변경
    private Object message;
    private String path;

    public ApiResponseEntity(int status, String error, Object message, String path) {
        this.timestamp = new Date();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

}
