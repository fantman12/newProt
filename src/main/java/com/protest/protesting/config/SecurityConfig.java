//package com.protest.protesting.config;
//
//import com.protest.protesting.filter.JwtAuthenticationFilter;
//import com.protest.protesting.utils.JwtUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.formLogin().disable()
//                .csrf().disable()
//                .cors().disable()
//                .headers().frameOptions().disable();
//
//        //JwtAuthentication필터 추가
//        http.addFilter(jwtAuthenticationFilter())
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//
//    @Bean
//    public BCryptPasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public JwtUtils jwtUtils() {
//        return new JwtUtils(secret);
//    }
//
//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
//        return new JwtAuthenticationFilter(authenticationManager(), jwtUtils());
//    }
//}
