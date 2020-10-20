package com.protest.protesting.entity;

import lombok.*;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor
public class VisitorEntity {

    private int seq;
    private Integer companySeq;
    private String birthday;
    private String name;
    private String qrCode;
    private String visitedAt;
    private String phoneNumber;
    private String affiliation;
    private String visitPurpose;
    private String manager;
    private int isPassed;
    private String createdAt;
    private String updatedAt;
    private int isDeleted; // 삭제 여부
}
