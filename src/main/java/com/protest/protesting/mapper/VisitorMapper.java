package com.protest.protesting.mapper;

import com.protest.protesting.entity.QuestionnairesEntity;
import com.protest.protesting.entity.VisitorEntity;
import com.protest.protesting.entity.VisitorListEntity;
import com.protest.protesting.entity.VisitorQrEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VisitorMapper {
    public void addVisitorInfo(VisitorEntity visitorEntity);
    public int getVisitorCurrentInfo(String startDate, String endDate);
    public int getVisitorPassInfo(String startDate, String endDate);
    public int getVisitorUnPassInfo(String startDate, String endDate);
    public List<VisitorEntity> getVisitorList(String startDate, String endDate, String keyword, int limit, int offset, Integer companySeq);
    public VisitorEntity getVisitor(int targetSeq);
    public List<QuestionnairesEntity> getVisitorQuestion(int targetSeq);
    public void updateVisitorQr(VisitorQrEntity visitorQrEntity); // faceone Agent 에서 Qr update 용도
//    public VisitorEntity updateVisitorQr(VisitorQrEntity visitorQrEntity); // faceone Agent 에서 Qr update 용도

    public VisitorEntity alreadyVisitorInfo(VisitorEntity visitorEntity);
    public void updateVisitorAt(VisitorEntity visitorEntity);
}
