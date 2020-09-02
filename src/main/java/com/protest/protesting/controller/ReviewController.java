package com.protest.protesting.controller;

import com.protest.protesting.entity.AccountEntity;
import com.protest.protesting.entity.ApiResponseEntity;
import com.protest.protesting.entity.LoginEntity;
import com.protest.protesting.mapper.AccountMapper;
import com.protest.protesting.utils.JwtUtils;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.util.Date;

@RestController
//@Log4j2
public class ReviewController {
    @Autowired
    protected AccountEntity accountEntity;

    @Autowired
    protected AccountMapper accountMapper;

    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private JwtUtils jwtUtils;

//    @PostMapping("/test")
//    public ResponseEntity auth(Authentication auth) throws URISyntaxException {
//        System.out.println(auth);
//
//        if(auth == null){
//            return ResponseEntity.badRequest().body("fail");
//        }
//
//        return ResponseEntity.ok().body("success");
//    }

//    @PostMapping("/loginTest")

//    @PostMapping("/login")
//    public Object getToken(@RequestBody LoginEntity loginEntity, HttpServletRequest request){
//        try {
//            if ("test@example.com".equalsIgnoreCase(loginEntity.getEmail()) && "1234".equals(loginEntity.getPassword())) {
//                accountEntity.setName(loginEntity.getEmail());
//                accountEntity = accountMapper.getUserByName(accountEntity);
//
//                if (ObjectUtils.isEmpty(accountEntity)) {
//                    return null;
//                }
//
//                Long id = 1L;
//                String name = accountEntity.getName();
////                return jwtUtils.createToken(id, name);
//            }
//        } catch (NullPointerException e) {
//            Date getDate = new Date();
//
//            ApiResponseEntity message = new ApiResponseEntity(getDate,
//                    HttpStatus.BAD_REQUEST.value(),
//                    e.toString(),
//                    "회원 정보가 존재하지 않습니다.",
//                    request.getServletPath());
//
//            System.out.println(message);
//
//
//            return message;
//        }
//
//        return null;
//    }

//    @PostMapping("/test/signin")
//    public Object testSignIn(@RequestBody AccountEntity accountEntity) {
//        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        String encPassword = passwordEncoder.encode(accountEntity.getPassword());
//
//        return encPassword;
//    }

}

//@ControllerAdvice
//public class ReviewErrorAdvice {
//
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(SignatureException.class)
//    public String NotValidToken(){
//        return "유효하지 않는 토큰입니다.";`
//    }
//}
