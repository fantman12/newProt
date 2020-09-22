package com.protest.protesting.service;

import com.protest.protesting.entity.LoginRequest;

public interface MemberService {
    public String createToken(LoginRequest loginRequest);
}
