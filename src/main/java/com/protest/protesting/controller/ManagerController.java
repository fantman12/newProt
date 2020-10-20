package com.protest.protesting.controller;

import com.protest.protesting.entity.*;
import com.protest.protesting.mapper.CompaniesMapper;
import com.protest.protesting.service.CompaniesService;
import com.protest.protesting.service.QuestionService;
import com.protest.protesting.utils.MainUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired QuestionService questionService;
    @Autowired MainUtils mainUtils;

    /**
     * 문진표 전부 조회 (Page 16)
     * @return
     */
    @GetMapping(value = "/questionRegistration")
    public ResponseEntity<ApiResponseEntity> questionManageSelect(
            @RequestParam(value = "limit", required = false, defaultValue = "20") int limit,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int offset) {
        List<QuestionnairesEntity> result = questionService.selectQuestionInfo(limit , offset);

        JSONObject json = new JSONObject();
        json.put("limit", limit);
        json.put("offset", offset);
        json.put("list", result);

        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(json), HttpStatus.OK);
    }

    /**
     *
     * 문진표 단일 삭제 (Page 16) (소프트 Delete)
     * @param qne
     * @return
     */
    @ApiOperation(value = "문진표 단일 삭제 (Page 16) (소프트 Delete)")
    @DeleteMapping(value = "/questionRegistration")
    public ResponseEntity<ApiResponseEntity> questionManageDelete(@RequestBody QuestionnairesEntity qne) {
        Object result = questionService.deleteQuestionInfo(qne);
        if (!result.equals(true)) {
            return new ResponseEntity<ApiResponseEntity>(mainUtils.badResponse("", "삭제할 데이터가 없습니다."), HttpStatus.OK);
        }
        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(""), HttpStatus.OK);
    }

    /**
     * 문진표 등록 (Page 16) (단일 등록)
     * @param questionEntity
     * @return
     */
    @PostMapping(value = "/questionRegistration")
    public ResponseEntity<ApiResponseEntity> questionManageInsert(@RequestBody QuestionEntity questionEntity) {

        Object result = questionService.insertQuestionInfo(questionEntity);
        if (result.equals(false)) {
            JSONObject jsonObject = new JSONObject();
            return new ResponseEntity<ApiResponseEntity>(mainUtils.badResponse(jsonObject, "문진표 등록에 실패하였습니다."), HttpStatus.OK);
        }
        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(result), HttpStatus.OK);
    }


    /**
     * 문진표 내용 수정 (Page 16) (단일 내용 수정)
     * @param questionnairesEntity
     * @return
     */
    @PutMapping(value = "/questionRegistration")
    public ResponseEntity<ApiResponseEntity> questionManageUpdate(@RequestBody QuestionnairesEntity questionnairesEntity) {

        Object result = questionService.updateQuestionInfo(questionnairesEntity);
        if (!result.equals(true)) {
            return new ResponseEntity<ApiResponseEntity>(mainUtils.badResponse(""), HttpStatus.OK);
        }
        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(""), HttpStatus.OK);
    }

    /**
     * 담당자 리스트 조회
     */
//    @GetMapping(value = "/infoList")
//    public ResponseEntity<ApiResponseEntity> managerInfoList() {
//
//    }

}
