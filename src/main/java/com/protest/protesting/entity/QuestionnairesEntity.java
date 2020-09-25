package com.protest.protesting.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@ToString
public class QuestionnairesEntity {

    private int seq;
    private String question;
    private String subQuestion;
    private int isVisibled;
    private int orderedBy;
    private String createdAt;
    private String updatedAt;
    private int isDeleted;

}
