package com.protest.protesting.utils;

import com.protest.protesting.entity.ApiResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class MainUtils {
    @Autowired HttpServletRequest httpServletRequest;

    public String getReqURI() {
        return httpServletRequest.getRequestURI();
    }

    public ApiResponseEntity successResponse(Object result) {
        return new ApiResponseEntity(
                HttpStatus.OK.value(),
                "",
                result,
                this.getReqURI()
        );
    }

    public ApiResponseEntity badResponse(Object result) {
        return new ApiResponseEntity(
                HttpStatus.BAD_REQUEST.value(),
                "",
                result,
                this.getReqURI()
        );
    }

    public ApiResponseEntity badResponse(Object result, String error) {
        return new ApiResponseEntity(
                HttpStatus.BAD_REQUEST.value(),
                error,
                result,
                this.getReqURI()
        );
    }

    public String getymdDateFormat(String pYear, String pMonth, String pDate, int dateOption) {
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(pYear), Integer.parseInt(pMonth), Integer.parseInt(pDate));
        cal.add(Calendar.DATE, dateOption);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date = cal.get(Calendar.DATE);
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);

        return year+"-"+month+"-"+date+ " " +hour+":"+minute+":"+second;
    }

    public String SimpleYmdDateFormat(String pYear, String pMonth, String pDate, int dateOption) {
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(pYear), Integer.parseInt(pMonth), Integer.parseInt(pDate));
        cal.add(Calendar.DATE, dateOption);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date = cal.get(Calendar.DATE);

        return year+"-"+month+"-"+date;
    }


    public String secretEncrypt(String str) {
        String MD5 = "";

        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for(int i = 0 ; i < byteData.length ; i++){
                sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
            }
            MD5 = sb.toString();

        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            MD5 = null;
        }

        return MD5;
    }
}
