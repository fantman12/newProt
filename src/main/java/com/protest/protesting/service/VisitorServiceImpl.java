package com.protest.protesting.service;

import com.protest.protesting.entity.*;
import com.protest.protesting.exception.BusinessException;
import com.protest.protesting.exception.ErrorCode;
import com.protest.protesting.mapper.CompaniesMapper;
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

    @Autowired
    CompaniesMapper companiesMapper;

    @Override
    public Object insertVisitorInfo(VisitorEntity ve) {
        VisitorEntity chkVisitor = null;
        CompaniesEntity ce = null;
        ce = companiesMapper.getCompanyInfo(ve.getCompanySeq());
        if (ce == null) {
            throw new BusinessException(ErrorCode.ERR_NOT_FOUND_COMPANY);
        }

        try {
            chkVisitor = visitorMapper.alreadyVisitorInfo(ve);
            if (chkVisitor != null) {
                visitorMapper.updateVisitorAt(chkVisitor);// 기존 Visitor 정보 존재시 시간 Update
                chkVisitor = visitorMapper.getVisitor(chkVisitor.getSeq());
            } else {
                visitorMapper.addVisitorInfo(ve); // 기존 Visitor 정보 없을시 Insert
                return visitorMapper.getVisitor(ve.getSeq());
            }
        } catch (Exception e) {
            e.getStackTrace();
            throw new BusinessException(ErrorCode.VISITOR_INFO_INSERT_ERR);
        }
        return chkVisitor;
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
    public List<VisitorEntity> getVisitorList(String startDate, String endDate, String keyword, int limit, int offset, Integer companySeq) {
        return visitorMapper.getVisitorList(startDate, endDate, keyword, limit, offset, companySeq);
    }

    @Override
    public VisitorEntity getVisitor(int targetSeq) {
         return visitorMapper.getVisitor(targetSeq);
    }


    @Override
    public List<QuestionnairesEntity> getVisitorQuestion(int targetSeq) {
        return visitorMapper.getVisitorQuestion(targetSeq);
    }

    @Override
    public VisitorEntity updateVisitorQr(VisitorQrEntity visitorQrEntity) {
        VisitorEntity ve = null;
        try {
            visitorMapper.updateVisitorQr(visitorQrEntity);
            int targetSeq = visitorQrEntity.getSeq();
            System.out.println(targetSeq);

            ve = visitorMapper.getVisitor(targetSeq);
            System.out.println(ve.getCreatedAt());
            System.out.println(ve.getUpdatedAt());

        } catch (Exception e) {
            e.getStackTrace();
            throw new BusinessException(ErrorCode.AGENT_QR_UPDATE_FAIL);
        }

        return ve;
    }

}
