package com.protest.protesting.controller;

import com.protest.protesting.entity.ApiResponseEntity;
import com.protest.protesting.entity.IamportInformationEntity;
import com.protest.protesting.service.IamportInformationService;
import com.protest.protesting.utils.MainUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dreamsecurity.crypt.*;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

@RestController
public class IamportController {
    @Autowired MainUtils mainUtils;
    @Autowired IamportInformationService iamportInformationService;

    @GetMapping("iamportModal")
    public ResponseEntity<ApiResponseEntity> getIamportModal() {

        MsgCrypto mscr = new MsgCrypto();

        //날짜 생성
        Calendar today = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String day = sdf.format(today.getTime());

        java.util.Random ran = new Random();
        //랜덤 문자 길이
        int numLength = 6;
        String randomStr = "";

        for (int i = 0; i < numLength; i++) {
            //0 ~ 9 랜덤 숫자 생성
            randomStr += ran.nextInt(10);
        }
        //reqNum은 최대 40byte 까지 사용 가능
        String reqNum = day + randomStr;
//        String rtn_url = "http://dev.mobile-ok.com/popup/js_test03.jsp"; // dream_test03.jsp
//        String rtn_url = "http://localhost:8080/iamportReturn"; // dream_test03.jsp
        String rtn_url = "http://54.180.22.14:8080/iamportReturn"; // dream_test03.jsp
        String urlCode = "01001";
        String cpId = "cjexpress";

        String reqdate = day;

        String reqInfo = urlCode + "/" + reqNum + "/" + reqdate;

//        String encReqInfo = mscr.msgEncrypt(reqInfo,"~/dreamsecurity/cert/cjexpressCert.der");			// 인증서 파일명, 경로
        String encReqInfo = mscr.msgEncrypt(reqInfo,"file://home/ubuntu/dreamsecurity/cert/cjexpressCert.der");			// 인증서 파일명, 경로
        encReqInfo = URLEncoder.encode(encReqInfo);

        String url = "https://www.mobile-ok.com/popup/common/hscert.jsp";
        String pathUrl = "?cpid="+ cpId +"&rtn_url="+ rtn_url +"&req_info=" + encReqInfo;

        String fullUrl = url + pathUrl;

        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(fullUrl), HttpStatus.OK);
    }

    @GetMapping("iamportReturn")
    public ResponseEntity<ApiResponseEntity> resultIamport(@RequestParam("priinfo") String encPriInfo) {
        String gender = "";					// 성별 정보
        String nation = "";					// 내국, 외국인 정보

        MsgCrypto mscr = new MsgCrypto();

        String rstInfo = mscr.msgDecrypt(encPriInfo,"file://home/ubuntu/dreamsecurity/cert/cjexpressPri.key","88888888","EUC-KR");			// 키 파일명, 경로, 비밀번호
        String[] rstInfoArray = rstInfo.split("\\$");

        System.out.println("rstInfo : [" + rstInfo + "]");
        System.out.println("rstInfoArray Size ::: " + rstInfoArray.length);

        JSONObject jsonObject = new JSONObject();

        if (rstInfoArray.length > 3) {
            if (rstInfoArray[6].equals("1"))
                gender = "남자";
            else
                gender = "여자";

            if (rstInfoArray[7].equals("0"))
                nation = "내국인";
            else
                nation = "외국인";

            if (rstInfoArray.length > 0) {
                jsonObject.put("result_code", rstInfoArray[0]);

                jsonObject.put("CI", rstInfoArray[1]);

                jsonObject.put("DI", rstInfoArray[2]);

                jsonObject.put("phone_number", rstInfoArray[3]);

                jsonObject.put("agency", rstInfoArray[4]);

                jsonObject.put("birthday", rstInfoArray[5]);

                jsonObject.put("gender", gender);

                jsonObject.put("nation", nation);

                jsonObject.put("name", rstInfoArray[8]);

                jsonObject.put("request_num", rstInfoArray[9]);

                jsonObject.put("request_at", rstInfoArray[10]);
            }
        } else {
            jsonObject.put("result_code", rstInfoArray[0]);
            jsonObject.put("error_message", rstInfoArray[1]);
        }

        iamportInformationService.insertIamportLog(jsonObject);

        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(jsonObject), HttpStatus.OK);
    }
}
