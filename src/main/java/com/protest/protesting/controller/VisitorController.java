package com.protest.protesting.controller;

import com.protest.protesting.entity.*;
import com.protest.protesting.service.VisitorQuestionnairesService;
import com.protest.protesting.service.VisitorService;
import com.protest.protesting.utils.MainUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.apache.ibatis.annotations.Param;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class VisitorController {
    @Autowired MainUtils mainUtils;

    @Autowired VisitorService visitorService;

    @Autowired VisitorQuestionnairesService visitorQuestionnairesService;


    @PostMapping("/addVisitor")
    public ResponseEntity<ApiResponseEntity> addVisitor(@RequestBody VisitorEntity ve) {
        Object result = visitorService.insertVisitorInfo(ve);
        if (!result.equals(true)) {
            return new ResponseEntity<ApiResponseEntity>(mainUtils.badResponse(""), HttpStatus.OK);
        }
        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(""), HttpStatus.OK);
    }

    @PostMapping("/addVisitQuestion")
    public ResponseEntity<ApiResponseEntity> addVisitQuestion(@RequestBody ArrayList<VisitorQuestionnairesEntity> vqe) {
        Boolean isResult = null;
        for (VisitorQuestionnairesEntity forVqe : vqe) {
            isResult = visitorQuestionnairesService.insertVisitQuestion(forVqe);
        }
        assert isResult != null;
        if (!isResult.equals(true)) {
            return new ResponseEntity<ApiResponseEntity>(mainUtils.badResponse(""), HttpStatus.OK);
        }

        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(""), HttpStatus.OK);
    }

    /**
     * 홈 (12 Page)
     * @return
     */
    @GetMapping("/visitorCurrent")
    public ResponseEntity<ApiResponseEntity> getVisitorCurrent() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String now = simpleDateFormat.format(date);


        String newDate = now + " 23:59:59";

        int nowCurrent = visitorService.getVisitorCurrentInfo(now + " 00:00:00", newDate);

        String[] nowArr = now.split("-");

        String afterCurrent = mainUtils.getymdDateFormat(nowArr[0], nowArr[1], nowArr[2], -7);
        int afterWeekCurrent = visitorService.getVisitorCurrentInfo(afterCurrent, newDate);

        String monthAfterCurrent = mainUtils.getymdDateFormat(nowArr[0], nowArr[1], nowArr[2], -30);
        int afterMonthCurrent = visitorService.getVisitorCurrentInfo(monthAfterCurrent, newDate);


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nowCurrent", nowCurrent);
        jsonObject.put("afterWeekCurrent", afterWeekCurrent);
        jsonObject.put("afterMonthCurrent", afterMonthCurrent);


        Map<String, Integer> map = visitorService.getVisitorPassInfo(now + " 00:00:00", newDate);

        log.info(String.valueOf(map.get("pass")));
        log.info(String.valueOf(map.get("unPass")));

        jsonObject.put("passCount", map.get("pass"));
        jsonObject.put("unPassCount", map.get("unPass"));
        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(jsonObject), HttpStatus.OK);
    }

    /**
     * 홈 > 방문자 관리 > 방명록 관리 (13 Page)
     * @param startDate
     * @param endDate
     * @param keyword
     * @param limit
     * @param offset
     * @param isPassed
     * @param isUnPassed
     * @return
     */
    @GetMapping("/visitorList")
    public ResponseEntity<ApiResponseEntity> getVisitorList(
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "limit", required = false) String limit,
            @RequestParam(value = "offset", required = false) String offset,
            @RequestParam(value = "isPassed", required = false) String isPassed,
            @RequestParam(value = "isUnPassed", required = false) String isUnPassed
            ) {

        List<VisitorEntity> list = visitorService.getVisitorList(startDate, endDate, keyword, limit, offset);

        List<VisitorEntity> parseList = new ArrayList<>();
        for (VisitorEntity ve : list) {
            if (isPassed != null && isPassed.equals("1")) {
                if (ve.getIsPassed() == 1) {
                    parseList.add(ve);
                }
            }

            if (isUnPassed != null && isUnPassed.equals("1")) {
                if (ve.getIsPassed() == 0) {
                    parseList.add(ve);
                }
            }
        }

        parseList = list;
        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(parseList), HttpStatus.OK);
    }


    /**
     * 방명록 상세 + 문진표 (14 Page)
     * @param targetSeq
     * @return
     */
    @GetMapping("/visitorInfo/{targetSeq}")
    public ResponseEntity<ApiResponseEntity> getVisitorInfo(@PathVariable int targetSeq) {

        VisitorEntity visitorEntity = visitorService.getVisitor(targetSeq);
        List<QuestionnairesEntity> qe = visitorService.getVisitorQuestion(targetSeq);

        JSONObject json = new JSONObject();
        json.put("visitorQuestion", qe);
        json.put("visitor", visitorEntity);
        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(json), HttpStatus.OK);
    }


}
