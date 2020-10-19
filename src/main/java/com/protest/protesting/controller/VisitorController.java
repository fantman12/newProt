package com.protest.protesting.controller;

import com.google.common.util.concurrent.ListenableFuture;
import com.protest.protesting.entity.*;
import com.protest.protesting.exception.BusinessException;
import com.protest.protesting.exception.ErrorCode;
import com.protest.protesting.service.VisitorQuestionnairesService;
import com.protest.protesting.service.VisitorService;
import com.protest.protesting.utils.MainUtils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

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

    /**
     * 문진표 작성 (Page 7)
     * @param vqe
     * @return
     */
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
            @RequestParam(value = "limit", required = false, defaultValue = "20") int limit,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
            @RequestParam(value = "isPassed", required = false) String isPassed,
            @RequestParam(value = "isUnPassed", required = false) String isUnPassed
            ) {

//        Bandwidth rateLimit = Bandwidth.simple(5, Duration.ofSeconds(1));
//        Bucket bucket = Bucket4j.builder().addLimit(rateLimit).build();
        // index 1 페어일떄 Blcok
//        if (bucket.tryConsume(1)) {
//            throw new BusinessException(ErrorCode.TRY_API_CALL_MAX);
//        }

        List<VisitorEntity> list = visitorService.getVisitorList(startDate, endDate, keyword, limit, offset);
        List<VisitorEntity> parseList = new ArrayList<>();

        JSONObject json = new JSONObject();
        json.put("limit", limit);
        json.put("offset", offset);


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
        json.put("list", parseList);
        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(json), HttpStatus.OK);
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

//    비동기 Testing
//    @GetMapping("/mTest")
//    public void mTest() {
//        String requestURL = "http://localhost:8080/visitorList";
//        try {
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder().url(requestURL).build();
//
//            for (int i = 0; i <= 5; i++) {
//                client.newCall(request).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Request request, IOException e) {
//                        System.out.println(e.toString());
//                    }
//
//                    @Override
//                    public void onResponse(Response response) throws IOException {
//                        System.out.println(response.body().string());
//                    }
//                });
//            }
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
//    }

    @GetMapping("vTest")
    public void vTest() {
        this.get("https://10.10.40.210/api/v1/group?app_key=edb65b00ec81dee6&sign=ff951fdaa6d3831251aad221b01b5369&timestamp=1603093240247");
//        this.get("http://localhost:8080/visitorList");
    }

    public void get(String requestURL) {
        try {
            OkHttpClient client = new OkHttpClient();
            client.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            Request request = new Request.Builder()
//                    .addHeader("x-api-key", RestTestCommon.API_KEY)
                    .url(requestURL)
                    .build(); //GET Request

            //동기 처리시 execute함수 사용
            Response response = client.newCall(request).execute();

            //출력
            String message = response.body().string();
            System.out.println(message);
        } catch (Exception e){
            System.err.println(e.toString());
        }
    }


    public void post(String requestURL, String jsonMessage) {
        try{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
//                    .addHeader("x-api-key", RestTestCommon.API_KEY)
                    .url(requestURL)
                    .post(com.squareup.okhttp.RequestBody.create(com.squareup.okhttp.MediaType.parse("application/json"), jsonMessage)) //POST로 전달할 내용 설정
                    .build();

            //동기 처리시 execute함수 사용
            Response response = client.newCall(request).execute();

            //출력
            String message = response.body().string();
            System.out.println(message);

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }


    /**
     * faceone Agent에서 QR 코드 set
     */
    @PutMapping("/faceone/qrCode")
    public ResponseEntity<ApiResponseEntity> getVisitorInfo(
            @RequestParam("qrCode") String qrCode,
            @RequestParam("name") String name
    ) {

        System.out.println(qrCode);
        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(""), HttpStatus.OK);
    }


}
