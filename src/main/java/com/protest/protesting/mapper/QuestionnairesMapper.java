package com.protest.protesting.mapper;

import com.protest.protesting.entity.QuestionEntity;
import com.protest.protesting.entity.QuestionnairesEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionnairesMapper {
    public List<QuestionnairesEntity> selectQuestion();
    public void insertQuestion(QuestionEntity questionEntity);
    public void updateQuestion(QuestionnairesEntity questionnairesEntity);
    public void deleteQuestion(QuestionnairesEntity questionnairesEntity);
}
