package com.protest.protesting.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class VisitorQuestionnairesEntity {

    private int seq;
    private int visitorSeq;
    private int questionnaireSeq;
    private int isAgree;
    private String createdAt;
    private String updatedAt;
    private int isDeleted;
}
