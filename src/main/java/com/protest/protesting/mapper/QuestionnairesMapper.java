package com.protest.protesting.mapper;

import com.protest.protesting.entity.QuestionEntity;
import com.protest.protesting.entity.QuestionPassEntity;
import com.protest.protesting.entity.QuestionnairesEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionnairesMapper {
    public List<QuestionnairesEntity> selectQuestion(String limit, String offset);
    public void insertQuestion(QuestionEntity questionEntity);
    public void updateQuestion(QuestionnairesEntity questionnairesEntity);
    public void deleteQuestion(QuestionnairesEntity questionnairesEntity);
    public List<QuestionnairesEntity> questionVisible();
    public List<QuestionPassEntity> questionPassInfo(String startDate, String endDate, String searchType, String limit, String offset);
    public QuestionnairesEntity getOneQuestion(QuestionnairesEntity questionnairesEntity);
    public int questionVisibleCnt();
    public int questionUsingCnt();
    public int questionMaxOrderBy();
}
