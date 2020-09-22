package com.protest.protesting.service;

import com.protest.protesting.entity.QuestionEntity;
import com.protest.protesting.entity.QuestionnairesEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

public interface QuestionService {
    public List<QuestionnairesEntity> selectQuestionInfo();
    public Object insertQuestionInfo(QuestionEntity questionEntity);
    public Object updateQuestionInfo(QuestionnairesEntity questionnairesEntity);
    public Object deleteQuestionInfo(QuestionnairesEntity questionnairesEntity);
}
