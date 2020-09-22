package com.protest.protesting.service;

import com.protest.protesting.entity.QuestionEntity;
import com.protest.protesting.entity.QuestionnairesEntity;
import com.protest.protesting.mapper.QuestionnairesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired QuestionnairesMapper questionnairesMapper;

    public List<QuestionnairesEntity> selectQuestionInfo() {
        return questionnairesMapper.selectQuestion();
    }


    public Object insertQuestionInfo(QuestionEntity questionEntity) {
        try {
            questionnairesMapper.insertQuestion(questionEntity);
        } catch (Exception e) {
            return e.getStackTrace();
        }
        return true;
    }


    public Object updateQuestionInfo(QuestionnairesEntity questionnairesEntity) {
        try {
            questionnairesMapper.updateQuestion(questionnairesEntity);
        } catch (Exception e) {
            return e.getStackTrace();
        }
        return true;
        //        JSONObject json = new JSONObject();
//        json.put("question", question.getQuestion());
//        json.put("subQuestion", question.getSubQuestion());

//        ApiResponseEntity apiResponseEntity = new ApiResponseEntity(
//                HttpStatus.OK.value(),
//                "",
//                json,
//                request.getRequestURI()
//        );
    }

    public Object deleteQuestionInfo(QuestionnairesEntity questionnairesEntity) {
        try {
            questionnairesMapper.deleteQuestion(questionnairesEntity);
        } catch (Exception e) {
            return e.getStackTrace();
        }
        return true;
    }

}
