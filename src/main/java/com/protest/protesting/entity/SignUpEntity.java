package com.protest.protesting.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignUpEntity {
    private String username;
    private String password;
    private String name;
    private String confirmPw;
}
