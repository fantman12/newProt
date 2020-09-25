package com.protest.protesting.service;

import com.protest.protesting.entity.QuestionnairesEntity;
import com.protest.protesting.entity.VisitorEntity;
import com.protest.protesting.entity.VisitorListEntity;
import com.protest.protesting.mapper.VisitorMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("VisitorService")
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    VisitorMapper visitorMapper;

    @Override
    public Object insertVisitorInfo(VisitorEntity ve) {

        try {
            visitorMapper.addVisitorInfo(ve);
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
            return false;
        }

        return true;
    }

    @Override
    public int getVisitorCurrentInfo(String startDate, String endDate) {
        return visitorMapper.getVisitorCurrentInfo(startDate, endDate);
    }
    @Override
    public Map<String, Integer> getVisitorPassInfo(String startDate, String endDate) {
         int pass = visitorMapper.getVisitorPassInfo(startDate, endDate);
         int unPass = visitorMapper.getVisitorUnPassInfo(startDate, endDate);

        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("pass", pass);
        hashMap.put("unPass", unPass);

        return hashMap;
    }

    @Override
    public List<VisitorEntity> getVisitorList(String startDate, String endDate, String keyword, String limit, String offset) {
        return visitorMapper.getVisitorList(startDate, endDate, keyword, limit, offset);
    }

    @Override
    public VisitorEntity getVisitor(int targetSeq) {
         return visitorMapper.getVisitor(targetSeq);
    }

    @Override
    public List<QuestionnairesEntity> getVisitorQuestion(int targetSeq) {
        return visitorMapper.getVisitorQuestion(targetSeq);
    }
}
