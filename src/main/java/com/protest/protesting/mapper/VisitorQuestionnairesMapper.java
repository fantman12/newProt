package com.protest.protesting.mapper;

import com.protest.protesting.entity.QuestionnairesEntity;
import com.protest.protesting.entity.VisitorQuestionnairesEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VisitorQuestionnairesMapper {
    public void insertVisitQuestion(VisitorQuestionnairesEntity vqe);
    public QuestionnairesEntity getTargetQuestionnaires(int questionnaireSeq);
}
