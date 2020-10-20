package com.protest.protesting.controller;

import com.protest.protesting.entity.ApiResponseEntity;
import com.protest.protesting.entity.CompaniesEntity;
import com.protest.protesting.service.CompaniesService;
import com.protest.protesting.utils.MainUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    CompaniesService companiesService;

    @Autowired
    MainUtils mainUtils;

    /**
     * 기관 정보 조회
     */
    @GetMapping(value = "/companyInfo/{targetSeq}")
    public ResponseEntity<ApiResponseEntity> getCompanyInfo(@PathVariable int targetSeq) {
        CompaniesEntity ce = companiesService.getCompanyInfo(targetSeq);
        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(ce), HttpStatus.OK);
    }

    @GetMapping(value = "/companyInfo")
    public ResponseEntity<ApiResponseEntity> getCompanyInfo() {
        List<CompaniesEntity> ce = companiesService.getCompanyList();
        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(ce), HttpStatus.OK);
    }

//    @PostMapping(value = "/addCompanyInfo")
//    private ResponseEntity<ApiResponseEntity> getCompanyInfo(@RequestBody CompaniesEntity companiesEntity) {
//
//
////        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(ce), HttpStatus.OK);
//
//        return null;
//    }
}
