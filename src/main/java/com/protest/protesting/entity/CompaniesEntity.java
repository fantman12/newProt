package com.protest.protesting.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class CompaniesEntity {
    private int seq;
    private int isQrcode;
    private int isCamera;
    private String createdAt;
    private String updatedAt;
    private int isDeleted;
}
