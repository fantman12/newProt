package com.protest.protesting.service;

import com.protest.protesting.entity.CompaniesEntity;
import com.protest.protesting.mapper.CompaniesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompaniesServiceImpl implements CompaniesService {
    @Autowired
    CompaniesMapper companiesMapper;

    @Override
    public CompaniesEntity getCompanyInfo(int targetSeq) {
        CompaniesEntity ce = companiesMapper.getCompanyInfo(targetSeq);
        return ce;
    }


    @Override
    public List<CompaniesEntity> getCompanyList() {
        List<CompaniesEntity> list = companiesMapper.getCompanyList();
        return list;
    }
}
