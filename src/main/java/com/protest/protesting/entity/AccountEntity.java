package com.protest.protesting.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter @Setter
@Component
public class AccountEntity {
    private Long id;
    private String name;
    private int age;
    private String password;

}
