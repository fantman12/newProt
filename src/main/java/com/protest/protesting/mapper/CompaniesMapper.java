package com.protest.protesting.mapper;

import com.protest.protesting.entity.CompaniesEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompaniesMapper {
    public CompaniesEntity getCompanyInfo(int targetSeq);
    public List<CompaniesEntity> getCompanyList();
    // 기관 정보 등록 Post는 우선 필요없다 (진입 포인트 없음)
}
