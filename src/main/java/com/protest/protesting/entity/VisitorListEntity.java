package com.protest.protesting.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;

@Getter @Setter
@AllArgsConstructor
public class VisitorListEntity {
    private String startDate;
    private String endDate;
    private String keyword;
    private int limit;
    private int offset;
    private int companySeq;
}
