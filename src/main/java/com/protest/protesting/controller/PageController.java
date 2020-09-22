package com.protest.protesting.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.protest.protesting.entity.ApiResponseEntity;
import com.protest.protesting.entity.LoginEntity;
import com.protest.protesting.entity.SignUpEntity;
import com.protest.protesting.entity.User;
import com.protest.protesting.service.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.NetworkInterface;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

@RestController
public class PageController {
    @Autowired UserService userService;


    private User user1;


    @GetMapping(value = "/loginPage")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/testings")
    public String testings() {
        return "testings";

//        NetworkInterface tt = NetworkInterface.getNetworkInterfaces();
//        NetworkInterface ttt = tt.getInetAddresses();
    }

    @GetMapping(value = "/user/test")
    public String testUser() {
        System.out.println("aaaa");
        return "aa";
    }


    @PostMapping(value = "/userSingIn")
    public ResponseEntity<ApiResponseEntity> userSingIn(@RequestBody LoginEntity loginEntity){
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("email", loginEntity.getEmail());
        jsonObject.put("password", loginEntity.getPassword());

        ApiResponseEntity apiResponseEntity = new ApiResponseEntity(new Date(), HttpStatus.OK.value(), "", jsonObject, "");

        return new ResponseEntity<ApiResponseEntity>(apiResponseEntity, HttpStatus.OK);
    }

    @GetMapping(value = "/testing")
    public String hello(){
        System.out.println("test");
        return "call test";
    }

    private void setup() {
        user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("pass1");
        user1.setAccountNonExpired(true);
        user1.setAccountNonLocked(true);
        user1.setName("USER1");
        user1.setCredentialsNonExpired(true);
        user1.setEnabled(true);
        user1.setAuthorities(AuthorityUtils.createAuthorityList("USER"));
    }

    @PostMapping("/signUp")
    public ResponseEntity<ApiResponseEntity> run(@RequestBody SignUpEntity signUpEntity, HttpServletRequest request) {

        String username = signUpEntity.getName();
        String name = signUpEntity.getName();
        String pw = signUpEntity.getPassword();
        String confirmPw = signUpEntity.getConfirmPw();


        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(name)
        || StringUtils.isEmpty(pw) || StringUtils.isEmpty(confirmPw)) {
            ApiResponseEntity resEntity = new ApiResponseEntity(
                    HttpStatus.BAD_REQUEST.value(),
                    "",
                    "필수값을 확인해주세요.",
                    request.getRequestURI()
            );

            return new ResponseEntity<ApiResponseEntity>(resEntity, HttpStatus.OK);
        }

        if (!pw.equals(confirmPw)) {
            ApiResponseEntity resEntity = new ApiResponseEntity(
                    HttpStatus.BAD_REQUEST.value(),
                    "",
                    "Not Match Password",
                    request.getRequestURI()
            );
            return new ResponseEntity<ApiResponseEntity>(resEntity, HttpStatus.OK);
        }


        user1 = new User();
        user1.setUsername(username);
        user1.setPassword(pw);
        user1.setRoles("ROLE_USER"); // const static
        user1.setAccountNonExpired(true);
        user1.setAccountNonLocked(true);
        user1.setName(name);
        user1.setCredentialsNonExpired(true);
        user1.setEnabled(true);
        user1.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER"));

        // 생성전 검증 필요
        userService.createUser(user1);

        Collection<? extends GrantedAuthority> authorities1 = user1.getAuthorities();
        Iterator<? extends GrantedAuthority> it = authorities1.iterator();
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) user1.getAuthorities();


        JSONObject obj = new JSONObject();
        obj.put("username", user1.getUsername());
        obj.put("name", user1.getName());
        obj.put("roles", user1.getRoles());

        ApiResponseEntity resEntity = new ApiResponseEntity(
                HttpStatus.OK.value(),
                "",
                obj,
                request.getRequestURI());
        return new ResponseEntity<ApiResponseEntity>(resEntity, HttpStatus.OK);
    }


}
