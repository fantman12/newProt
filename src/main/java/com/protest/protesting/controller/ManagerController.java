package com.protest.protesting.controller;

import com.protest.protesting.entity.ApiResponseEntity;
import com.protest.protesting.entity.AuthenticationToken;
import com.protest.protesting.entity.QuestionEntity;
import com.protest.protesting.entity.QuestionnairesEntity;
import com.protest.protesting.service.QuestionService;
import com.protest.protesting.utils.MainUtils;
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
     * Select All (문진표)
     * @return
     */
    @GetMapping(value = "/questionRegistration")
    public ResponseEntity<ApiResponseEntity> questionManageSelect() {
        List<QuestionnairesEntity> result = questionService.selectQuestionInfo();

        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(result), HttpStatus.OK);
    }

    @DeleteMapping(value = "/questionRegistration")
    public ResponseEntity<ApiResponseEntity> questionManageDelete(@RequestBody QuestionnairesEntity qne) {
        questionService.deleteQuestionInfo(qne);
        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(""), HttpStatus.OK);
    }

    @PostMapping(value = "/questionRegistration")
    public ResponseEntity<ApiResponseEntity> questionManageInsert(@RequestBody QuestionEntity questionEntity) {

        Object result = questionService.insertQuestionInfo(questionEntity);
        if (!result.equals(true)) {
            return new ResponseEntity<ApiResponseEntity>(mainUtils.badResponse(""), HttpStatus.OK);
        }
        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(""), HttpStatus.OK);
    }


    @PutMapping(value = "/questionRegistration")
    public ResponseEntity<ApiResponseEntity> questionManageUpdate(@RequestBody QuestionnairesEntity questionnairesEntity) {

        Object result = questionService.updateQuestionInfo(questionnairesEntity);
        if (!result.equals(true)) {
            return new ResponseEntity<ApiResponseEntity>(mainUtils.badResponse(""), HttpStatus.OK);
        }
        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(""), HttpStatus.OK);
    }
}
