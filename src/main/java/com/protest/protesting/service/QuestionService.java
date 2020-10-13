package com.protest.protesting.service;

import com.protest.protesting.entity.QuestionEntity;
import com.protest.protesting.entity.QuestionPassEntity;
import com.protest.protesting.entity.QuestionnairesEntity;

import java.util.List;

public interface QuestionService {
    public List<QuestionnairesEntity> selectQuestionInfo(int limit, int offset);
    public Object insertQuestionInfo(QuestionEntity questionEntity);
    public Object updateQuestionInfo(QuestionnairesEntity questionnairesEntity);
    public Object deleteQuestionInfo(QuestionnairesEntity questionnairesEntity);
    public List<QuestionnairesEntity> getQuestionVisible();
    public List<QuestionPassEntity> questionPassInfo(String startDate, String endDate, String searchType, String limit, String offset);
}
