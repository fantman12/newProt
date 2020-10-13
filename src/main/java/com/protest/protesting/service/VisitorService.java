package com.protest.protesting.service;

import com.protest.protesting.entity.QuestionnairesEntity;
import com.protest.protesting.entity.VisitorEntity;
import com.protest.protesting.entity.VisitorListEntity;

import java.util.List;
import java.util.Map;

public interface VisitorService {
    public Object insertVisitorInfo(VisitorEntity ve);
    public int getVisitorCurrentInfo(String startDate, String endDate);
    public Map<String, Integer> getVisitorPassInfo(String startDate, String endDate);
    public List<VisitorEntity> getVisitorList(String startDate, String endDate, String keyword, int limit, int offset);
    public VisitorEntity getVisitor(int targetSeq);
    public List<QuestionnairesEntity> getVisitorQuestion(int targetSeq);
}
