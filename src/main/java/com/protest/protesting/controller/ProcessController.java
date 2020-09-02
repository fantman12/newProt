package com.protest.protesting.controller;

import com.protest.protesting.entity.ApiResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessController {


    @PostMapping(value = "/pingMock")
    public void targetResponse() {


    }
}
