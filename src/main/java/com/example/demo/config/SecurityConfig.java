package com.example.demo.config;

import com.example.demo.security.CustomAccessDeniedHandler;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtAuthenticationFilter;
import com.example.demo.security.JwtAuthorizationFilter;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

// Lombok을 통해 Logger와 LoggerFactory를 자동으로 등록
@Log
// 웹 보안과 관련된 방어기능을 활성화시킴
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 정확하게 보려면 HttpSecurity의 implements 확인
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("Security Configuration");

        // 접근 거부, URL 요청이 올바른지, 권한을 가지고 있는지 등등을 설정하는 것
        // 그냥 가져다 써...
        http.cors()
                .and()
                .csrf().disable()
                .exceptionHandling()
                // 커스텀 구간 createAccessDeinedHandler
                .accessDeniedHandler(createAccessDeniedHandler())
                .and()
                // 커스텀 구간 JwtAuthenticationFilter
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                // 커스텀 구간 JwtAuthorizationFilter
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    // 비밀번호를 암호화하기 위한 설정
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(createUserDetailsService())
                .passwordEncoder(createPasswordEncoder());
    }
    // createPasswordEncoder, createUserDetailsService bean이 Override에 묶여있음.
    @Bean
    public PasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService createUserDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 어떠한 형식의 URL 요청이나 Header 요청이든 수락.
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        // 외부에서 들어오는 Method도 수락.
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        config.setExposedHeaders(Arrays.asList("Authorization", "Content-Disposition"));

        source.registerCorsConfiguration("/**", config);

        return source;
    }

    // 접근 거부와 관련된 생성자 Security 폴더의 CustomAccessDeniedHandler를 리턴
    // 커스텀 요소
    @Bean
    public AccessDeniedHandler createAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
}