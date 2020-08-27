package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class AuthUtil {
    // header를 받아서 num을 추출
    // Header 정보를 날릴 때 jwt 형식의 데이터를 날려서 식별할 수 있게 해야함.
    public static long getUserNo(String header) throws Exception {
        String token = header.substring(7);

        byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();

        // parsedToken은 아래의 subject까지 포함하여 디코딩된 데이터를 얻는다.
        Jws<Claims> parsedToken = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token);

        String subject = parsedToken.getBody().getSubject();

        // String을 int로 만들어서 실제 정보로 사용함.
        long userNo = Integer.parseInt(subject);

        return userNo;
    }
}
