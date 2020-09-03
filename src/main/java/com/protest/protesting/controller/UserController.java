package com.protest.protesting.controller;

import com.protest.protesting.entity.ApiResponseEntity;
import com.protest.protesting.entity.AuthenticationRequest;
import com.protest.protesting.entity.AuthenticationToken;
import com.protest.protesting.entity.User;
import com.protest.protesting.service.UserService;
import com.protest.protesting.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class UserController {
    @Autowired UserService userService;

//    @Autowired AuthenticationManager authenticationManagers;
//
//    @Autowired private JwtUtils jwtUtils;
//
//
//    @PostMapping(value = "/login")
//    public ResponseEntity<ApiResponseEntity> login(@RequestBody AuthenticationRequest authenticationRequest, HttpServletRequest req) {
//        String username = authenticationRequest.getUsername();
//        String password = authenticationRequest.getPassword();
//
//        // User Scheme setting Required
//        UsernamePasswordAuthenticationToken toekn = new UsernamePasswordAuthenticationToken(username, password);
//        Authentication authentication = authenticationManagers.authenticate(toekn);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//
//        // session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
//        User user = userService.readUser(username); // UserDetailsService의 readUser 활용
//        // Jwt 형태
//        String jwt = jwtUtils.createToken(username, user.getRoles());
//
//        ApiResponseEntity apiResponseEntity = new ApiResponseEntity(
//                new Date(),
//                HttpStatus.OK.value(),
//                "",
//                new AuthenticationToken(user.getName(), user.getAuthorities(), jwt),
//                req.getRequestURI());
//
//        return new ResponseEntity<ApiResponseEntity>(apiResponseEntity, HttpStatus.OK);
//
//        // Jwt 리턴 구조 형태
////        return new AuthenticationToken(user.getName(), user.getAuthorities(), jwt);
//    }


}
