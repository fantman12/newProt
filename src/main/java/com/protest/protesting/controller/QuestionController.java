package com.protest.protesting.controller;

import com.protest.protesting.entity.ApiResponseEntity;
import com.protest.protesting.entity.QuestionPassEntity;
import com.protest.protesting.entity.QuestionnairesEntity;
import com.protest.protesting.service.QuestionService;
import com.protest.protesting.utils.MainUtils;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class QuestionController {
    @Autowired QuestionService questionService;

    @Autowired MainUtils mainUtils;

    /**
     * 문진표 문항 (Page 15) (문항 노출 On 인것만 조회)
     * @return
     */
    @GetMapping("/questionList")
    public ResponseEntity<ApiResponseEntity> getQuestionnaires() {
        List<QuestionnairesEntity> list = questionService.getQuestionVisible();
        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(list), HttpStatus.OK);
    }

    /**
     * 문진표 현황 (Page 15) 검색기능 완료
     * @return
     */
    @GetMapping("/questionPassInfo")
    public ResponseEntity<ApiResponseEntity> getVisitorPassInfo(
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "limit", required = false) String limit,
            @RequestParam(value = "offset", required = false) String offset
    ) {


        if (searchType != null) {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String parseDate = simpleDateFormat.format(date);

            String[] parseArr = parseDate.split("-");

            endDate = parseDate + " 23:59:59";
            switch (searchType) {
                case "day" :
                    startDate = mainUtils.SimpleYmdDateFormat(parseArr[0], parseArr[1], parseArr[2], -1);
                    startDate = startDate + " 00:00:00";
                    break;
                case "week" :
                    startDate = mainUtils.SimpleYmdDateFormat(parseArr[0], parseArr[1], parseArr[2], -7);
                    startDate = startDate + " 00:00:00";
                    break;

                case "month" :
                    startDate = mainUtils.SimpleYmdDateFormat(parseArr[0], parseArr[1], parseArr[2], -30);
                    startDate = startDate + " 00:00:00";
                    break;

                default:
                    break;
            }
        }

        System.out.println(startDate);
        System.out.println(endDate);

        List<QuestionPassEntity> list = questionService.questionPassInfo(startDate, endDate, searchType, limit, offset);

        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(list), HttpStatus.OK);
    }


    /**
     * 인증 방법 설정 (18 Page)
     * @return
     */
    @PutMapping("visitor/authMethod")
    public ResponseEntity<ApiResponseEntity> editAuthMethod() {


        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(""), HttpStatus.OK);
    }


}
