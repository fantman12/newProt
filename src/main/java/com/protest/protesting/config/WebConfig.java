//package com.protest.protesting.config;
//
//import com.protest.protesting.interceptor.JwtInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    private static final String[] EXCLUDE_PATHS = {
//            "/member/**",
//            "/error/**"
//    };
//
//    @Autowired
//    private JwtInterceptor jwtInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(jwtInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns(EXCLUDE_PATHS);
//    }
//}
