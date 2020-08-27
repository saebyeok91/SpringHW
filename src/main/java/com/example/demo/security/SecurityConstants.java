package com.example.demo.security;

public class SecurityConstants {
    public static final String AUTH_LOGIN_URL = "/api/authenticate";
    // http://www.allkeysgenerator.com
    public static final String JWT_SECRET =
            "ThVmYq3t6w9z$C&F)J@NcRfUjXnZr4u7x!A%D*G-KaPdSgVkYp3s5v8y/B?E(H+M";

    // JWT 기본 토큰
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";
}