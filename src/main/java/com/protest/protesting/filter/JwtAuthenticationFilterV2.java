//package com.protest.protesting.filter;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.protest.protesting.entity.LoginEntity;
//import com.protest.protesting.utils.JwtUtils;
//import io.jsonwebtoken.Claims;
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.apache.logging.log4j.util.Strings;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.nio.file.attribute.UserPrincipal;
//import java.util.ArrayList;
//
//@Order(Ordered.HIGHEST_PRECEDENCE)
////@RequiredArgsConstructor
//public class JwtAuthenticationFilterV2 extends BasicAuthenticationFilter {
//
//    private JwtUtils jwtUtils;
//
//
//
//    public JwtAuthenticationFilterV2(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
//        super(authenticationManager);
//        this.jwtUtils = jwtUtils;
//    }
//
//    /**
//     * SecurityContext 에 Authentication 객체 세팅.
//     * */
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain chain) throws IOException, ServletException {
//
//        System.out.println(request);
//
//
//        Authentication authentication = getAuthentication(request);
//        if (authentication != null) {
//            SecurityContext context = SecurityContextHolder.getContext();
//            context.setAuthentication(authentication);
//        }
//        chain.doFilter(request, response);
//    }
//
//    /**
//     * 헤더의 Authorization로부터 토큰을 가져와 사용자의 정보를 담을 Authentication 객체로 변환
//     * */
//    private Authentication getAuthentication(HttpServletRequest request) {
//        String token = request.getHeader("Authorization");
//        if (Strings.isEmpty(token)) {
//            return null;
//        }
//
//        System.out.println(token);
//        System.out.println(token.substring("Bearer ".length()));
//
////        Claims claims = jwtUtils.getClaims(token.substring("Bearer ".length()));
//        Claims claims = jwtUtils.getClaims(token);
//
//        System.out.println(claims);
//
//        return new UsernamePasswordAuthenticationToken(claims, null);
//    }
//}
