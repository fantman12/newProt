package com.protest.protesting.service;

import com.protest.protesting.entity.QuestionnairesEntity;
import com.protest.protesting.entity.VisitorEntity;
import com.protest.protesting.entity.VisitorQuestionnairesEntity;
import com.protest.protesting.mapper.VisitorMapper;
import com.protest.protesting.mapper.VisitorQuestionnairesMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("VisitorQuestionnairesService")
public class VisitorQuestionnairesServiceImpl implements VisitorQuestionnairesService {

    @Autowired
    VisitorQuestionnairesMapper visitorQuestionnairesMapper;

    @Autowired
    VisitorMapper visitorMapper;

    @Override
    public Boolean insertVisitQuestion(VisitorQuestionnairesEntity vqe) {

        try {
            VisitorEntity ve = visitorMapper.getVisitor(vqe.getSeq());
            if (ve.getSeq() != vqe.getSeq()) {
                throw new Exception();
            }

            QuestionnairesEntity qe = visitorQuestionnairesMapper.getTargetQuestionnaires(vqe.getQuestionnaireSeq());
            if (qe.getSeq() != vqe.getQuestionnaireSeq()) {
                throw new Exception();
            }
            visitorQuestionnairesMapper.insertVisitQuestion(vqe);
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }

        return true;
    }
}
