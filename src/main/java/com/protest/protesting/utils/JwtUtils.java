package com.protest.protesting.utils;


import com.auth0.jwt.interfaces.Claim;
import com.protest.protesting.entity.User;
import com.protest.protesting.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.*;

//@RequiredArgsConstructor
//@Component
@Slf4j
public class JwtUtils {
    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UserDetailsService userDetailsService;

    private static final String SALT =  "luvookSecret";

//    public JwtUtils(String secret) {
//        this.key = Keys.hmacShaKeyFor(secret.getBytes());
//    }

    public JwtUtils(String secret) {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    private byte[] generateKey(){
        byte[] key = null;
        try {
            key = SALT.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            if(log.isInfoEnabled()){
                e.printStackTrace();
            }else{
                log.error("Making JWT Key Error ::: {}", e.getMessage());
            }
        }

        return key;
    }

    public String createJwt(String key, User user){
        Claims claims = Jwts.claims();
        claims.put("username", key); // claims username, roles로 생성
        claims.put("name", user.getName()); // 정보는 key / value 쌍으로 저장된다.

        Date now = new Date();

        long tokenValidTime = 240 * 60 * 1000L;


        String jwt = Jwts.builder()
                .setHeaderParam("typ", "JWT")
//                .setHeaderParam("regDate", System.currentTimeMillis())
//                .setSubject(subject)
                .setClaims(claims)
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, this.generateKey())
                .compact();
        return jwt;
    }

    public boolean isUsable(String jwt) {
        try{
            // 존재성 체크
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(this.generateKey())
                    .parseClaimsJws(jwt);

            // 만료 시간 체크
            return !claims.getBody().getExpiration().before(new Date());

        }catch (Exception e) {
            throw new UnauthorizedException();
        }
    }

//    public String createToken(String id, String roles) {
//        Claims claims = Jwts.claims();
////        claims.put("username", id); // claims username, roles로 생성
////        claims.put("roles", roles); // 정보는 key / value 쌍으로 저장된다.
//
//        Date now = new Date();
//
//        long tokenValidTime = 30 * 60 * 1000L;
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(now) // 토큰 발행 시간 정보
//                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
////                .signWith(SignatureAlgorithm.HS256, key)
//                .signWith(SignatureAlgorithm.HS256, secret)
//                .compact();
//
////        return Jwts.builder()
////                .setIssuedAt(now)
////                .setExpiration(new Date(now.getTime() + tokenValidTime))
////                .setSubject("Joe")
////                .signWith(getSigningKey())
////                .compact();
//
//    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserPk(String token) {
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SALT.getBytes("UTF-8"))
                    .parseClaimsJws(token);
        } catch (Exception e) {
            throw new UnauthorizedException();
        }

        return (String) claims.getBody().get("username");
    }

    public String resolveToken(HttpServletRequest request) {

        return request.getHeader("Authorization");
    }
}
