package com.protest.protesting.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.protest.protesting.entity.*;
import com.protest.protesting.exception.BusinessException;
import com.protest.protesting.exception.ErrorCode;
import com.protest.protesting.exception.FileUploadException;
import com.protest.protesting.property.FileUploadProperties;
import com.protest.protesting.service.FileUploadDownloadService;
import com.protest.protesting.service.UserService;
import com.protest.protesting.utils.JwtUtils;
import com.protest.protesting.utils.MainUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.net.NetworkInterface;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

@RestController
public class PageController {
    @Autowired UserService userService;

    @Autowired MainUtils mainUtils;

    private User user1;

    @Autowired FileUploadDownloadService fileUploadDownloadService;

    @GetMapping(value = "/loginPage")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/testings")
    public Object testings() {

//        System.out.println(new Timestamp(System.currentTimeMillis()));
//        System.out.println(System.currentTimeMillis());

        long t = System.currentTimeMillis();

        System.out.println(t);
        String secret = mainUtils.secretEncrypt(t+"#cac5782e7df4a8166429b617853cdfbd");
        System.out.println(secret);

        return new Timestamp(System.currentTimeMillis());

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
            throw new BusinessException(ErrorCode.USER_SIGN_UP_REQUIRED);
        }

        if (!pw.equals(confirmPw)) {
            throw new BusinessException(ErrorCode.USER_SIGN_UP_NOT_MATCH_PW);
        }


        user1 = new User();
        user1.setUsername(username);
        user1.setPassword(pw);
        user1.setRoles("ROLE_USER"); // const static
//        user1.setRoles("ROLE_ADMIN"); // const static
        user1.setAccountNonExpired(true);
        user1.setAccountNonLocked(true);
        user1.setName(name);
        user1.setCredentialsNonExpired(true);
        user1.setEnabled(true);
        user1.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER"));
//        user1.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_ADMIN"));

        // 생성전 검증 필요
        userService.createUser(user1);

        Collection<? extends GrantedAuthority> authorities1 = user1.getAuthorities();
        Iterator<? extends GrantedAuthority> it = authorities1.iterator();
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) user1.getAuthorities();


        JSONObject obj = new JSONObject();
        obj.put("username", user1.getUsername());
        obj.put("name", user1.getName());
        obj.put("roles", user1.getRoles());

        return new ResponseEntity<ApiResponseEntity>(mainUtils.successResponse(""), HttpStatus.OK);
    }


    @PostMapping("/uploadFile")
    public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file) {
        Path fileLocation = Paths.get("/uploads")
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(fileLocation);
        }catch(Exception e) {
            throw new FileUploadException("파일을 업로드할 디렉토리를 생성하지 못했습니다.", e);
        }


        String fileName = fileUploadDownloadService.storeFile(file, fileLocation);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new FileUploadResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

}
