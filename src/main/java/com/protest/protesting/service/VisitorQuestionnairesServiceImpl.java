package com.protest.protesting.service;

import com.protest.protesting.entity.VisitorQuestionnairesEntity;
import com.protest.protesting.mapper.VisitorQuestionnairesMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("VisitorQuestionnairesService")
public class VisitorQuestionnairesServiceImpl implements VisitorQuestionnairesService {

    @Autowired
    VisitorQuestionnairesMapper visitorQuestionnairesMapper;

    @Override
    public Boolean insertVisitQuestion(VisitorQuestionnairesEntity vqe) {

        try {
            visitorQuestionnairesMapper.insertVisitQuestion(vqe);
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }

        return true;
    }
}
