//package com.protest.protesting.interceptor;
//
//import com.protest.protesting.exception.UnauthorizedException;
//import com.protest.protesting.service.JwtService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class JwtInterceptor implements HandlerInterceptor {
//    private static final String HEADER_AUTH = "Authorization";
//
//    @Autowired
//    private JwtService jwtService;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//        final String token = request.getHeader(HEADER_AUTH);
//
//        if(token != null && jwtService.isUsable(token)){
//            return true;
//        }else{
//            throw new UnauthorizedException();
//        }
//    }
//}
