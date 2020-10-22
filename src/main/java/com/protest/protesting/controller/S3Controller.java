package com.protest.protesting.controller;

import com.protest.protesting.entity.ApiResponseEntity;
import com.protest.protesting.entity.S3Entity;
import com.protest.protesting.service.S3Service;
import com.protest.protesting.utils.MainUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/image")
public class S3Controller {
    private S3Service s3Service;

    @Autowired MainUtils mainUtils;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponseEntity> execWrite(@RequestParam("file") MultipartFile file,
                                                       @RequestParam("visitorSeq") int visitorSeq) throws IOException {
        String imgPath = s3Service.upload(file);

        S3Entity s3Entity = new S3Entity();
        s3Entity.setVisitor_seq(visitorSeq);
        s3Entity.setImage_url(imgPath);

        S3Entity returnObject = s3Service.insertVisitorImage(s3Entity);
        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(returnObject), HttpStatus.OK);
    }

    @GetMapping("/getInfo/{visitorSeq}")
    public ResponseEntity<ApiResponseEntity> getImageInfo(@PathVariable int visitorSeq) {
        S3Entity s3Entity = s3Service.selectVisitorImage(visitorSeq);
        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(s3Entity), HttpStatus.OK);
    }
}
