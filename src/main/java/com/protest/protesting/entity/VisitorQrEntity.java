package com.protest.protesting.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class VisitorQrEntity {
    private int seq;
    private String qrCode;
    private String name;
    private int companySeq;
    private String phoneNumber;

}
