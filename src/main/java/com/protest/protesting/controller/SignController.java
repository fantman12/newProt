package com.protest.protesting.controller;

import com.protest.protesting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class SignController {

    @Autowired
    private UserService userService;


}
