package com.protest.protesting.utils;


import com.auth0.jwt.interfaces.Claim;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;

//@RequiredArgsConstructor
//@Component
public class JwtUtils {
//    private final Key key;

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UserDetailsService userDetailsService;

//    public JwtUtils(String secret) {
//        this.key = Keys.hmacShaKeyFor(secret.getBytes());
//    }

    public JwtUtils(String secret) {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

//    @PostConstruct
//    protected void init() {
//        secret = Base64.getEncoder().encodeToString(secret.getBytes());
//    }

//    public String createToken(String id, String name) {
//        Date now = new Date();
//
//        long tokenValidTime = 30 * 60 * 1000L;
//
//        return Jwts.builder()
//                .claim("id", id)
//                .claim("name", name)
//                .setIssuedAt(now) // 토큰 발행 시간 정보
//                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//    }


    public String createToken(String id, String roles) {
        Claims claims = Jwts.claims();
        claims.put("username", id); // claims username, roles로 생성
        claims.put("roles", roles); // 정보는 key / value 쌍으로 저장된다.

        Date now = new Date();

        long tokenValidTime = 30 * 60 * 1000L;

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
//                .signWith(key, SignatureAlgorithm.HS256)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

//    public Claims getClaims(String token) {
//
//        System.out.println(key);
//        System.out.println("12312322");
//        System.out.println(token);
//
//        return Jwts.parser()
//                .setSigningKey(key)
//                .parseClaimsJws(token)
//                .getBody();
//    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        System.out.println(jwtToken);


        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        System.out.println(request);

        return request.getHeader("Authorization");
    }
}
