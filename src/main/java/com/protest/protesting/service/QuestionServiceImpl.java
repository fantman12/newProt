package com.protest.protesting.service;

import com.protest.protesting.entity.QuestionEntity;
import com.protest.protesting.entity.QuestionPassEntity;
import com.protest.protesting.entity.QuestionnairesEntity;
import com.protest.protesting.exception.BusinessException;
import com.protest.protesting.exception.CustomException;
import com.protest.protesting.exception.ErrorCode;
import com.protest.protesting.mapper.QuestionnairesMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired QuestionnairesMapper questionnairesMapper;

    public List<QuestionnairesEntity> selectQuestionInfo(int limit, int offset) {
        return questionnairesMapper.selectQuestion(limit, offset);
    }


    public Object insertQuestionInfo(QuestionEntity questionEntity) {
        JSONObject json = new JSONObject();
        int cnt = questionnairesMapper.questionUsingCnt();
        if (cnt >= 5) {
            throw new BusinessException(ErrorCode.QUESTION_OVER_COUNT);
        }

        int maxOrderedBy = 0;
        if (cnt > 0) {
            maxOrderedBy = questionnairesMapper.questionMaxOrderBy();
        }
        questionEntity.setOrderedBy(maxOrderedBy + 1);
        questionnairesMapper.insertQuestion(questionEntity);

        json.put("seq", questionEntity.getSeq());
        json.put("question", questionEntity.getQuestion());
        json.put("subQuestion", questionEntity.getSubQuestion());

        return json;
    }


    public Object updateQuestionInfo(QuestionnairesEntity questionnairesEntity) {
        try {
            int visibleCnt = questionnairesMapper.questionVisibleCnt();
            if (visibleCnt >= 5) {
                return false;
            }

            questionnairesMapper.updateQuestion(questionnairesEntity);
        } catch (Exception e) {
            return e.getStackTrace();
        }
        return true;

    }

    public Object deleteQuestionInfo(QuestionnairesEntity questionnairesEntity) {
        try {
            QuestionnairesEntity qe = questionnairesMapper.getOneQuestion(questionnairesEntity);
            System.out.println(qe);
            if (qe == null) {
                return false;
            }
            questionnairesMapper.deleteQuestion(questionnairesEntity);

        } catch (Exception e) {
            return e.getStackTrace();
        }
        return true;
    }


    @Override
    public List<QuestionnairesEntity> getQuestionVisible() {
        return questionnairesMapper.questionVisible();
    }

    @Override
    public List<QuestionPassEntity> questionPassInfo(String startDate, String endDate, String searchType, String limit, String offset) {
        return questionnairesMapper.questionPassInfo(startDate, endDate,searchType, limit, offset);
    }

}
