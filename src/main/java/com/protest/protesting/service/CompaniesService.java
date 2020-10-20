package com.protest.protesting.service;

import com.protest.protesting.entity.CompaniesEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CompaniesService {
    public CompaniesEntity getCompanyInfo(int targetSeq);
    public List<CompaniesEntity> getCompanyList();
}
