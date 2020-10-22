package com.protest.protesting.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.protest.protesting.utils.MainUtils;

import javax.servlet.http.HttpServletRequest;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", " Invalid Input Value"),
    ENTITY_NOT_FOUND(400, "C003", " Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
    INVALID_TYPE_VALUE(400, "C005", " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),


    EMAIL_DUPLICATION(400, "M001", "Email is Duplication"),
    LOGIN_INPUT_INVALID(400, "M002", "Login input is invalid"),

    // Coupon
    COUPON_ALREADY_USE(400, "CO001", "Coupon was already used"),
    COUPON_EXPIRE(400, "CO002", "Coupon was already expired"),

    // Question
    QUESTION_OVER_COUNT(400, "Q001", "문진표 사용중인 개수가 5개를 넘을수없습니다."),
    QUESTION_ORDER_COUNT(400, "Q002", "문진표 순서는 5개를 넘을수없습니다."),
    QUESTION_NOT_FOUND(400, "Q003", "삭제할 문진표 정보가 존재하지 않습니다."),

    // Visitor
    VISITOR_INFO_INSERT_ERR(400, "V001", "방문자 정보 저장에 실패하였습니다."),

    // User Login / SignUp
    USER_SIGN_UP_REQUIRED(400, "S001", "필수값을 확인해주세요."),
    USER_SIGN_UP_NOT_MATCH_PW(400, "S002", "입력하신 비밀번호가 일치하지 않습니다."),
    USER_SIGN_UP_ALREADY(400, "S003", "해당 사용자이름은 중복됩니다. 다른 이름을 입력해주세요."),
    USER_SIGN_UP_EMPTY(400, "S004", "회원정보가 존재하지 않습니다."),

    // Api LateLimit
    TRY_API_CALL_MAX(400, "T001", "API 요청이 너무 많습니다."),

    // QR Update Error
    AGENT_QR_UPDATE_FAIL(400, "QR001", "QR 코드 정보 업데이트에 실패하였습니다."),

    // Company
    ERR_NOT_FOUND_COMPANY(400, "CO001", "기관 정보가 존재하지 않습니다."),

    // S3 Image Error
    ERR_S3_UPLOAD(400, "U001", "이미지 업로드에 실패하였습니다."),
    ERR_S3_NOT_FOUND(400, "U002", "이미지 정보가 존재하지 않습니다."),

    ;
    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }

}