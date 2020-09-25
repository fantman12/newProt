package com.protest.protesting.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class QuestionPassEntity {
    private String visitedAt;
    private int passCnt;
    private int unPassCnt;

}
