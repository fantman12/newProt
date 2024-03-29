package com.protest.protesting.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class QuestionEntity {
    private int seq;
    private String question;
    private String subQuestion;
    private int orderedBy;
}
