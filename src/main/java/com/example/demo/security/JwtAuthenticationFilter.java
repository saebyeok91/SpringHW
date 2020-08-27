package com.example.demo.security;

import com.example.demo.security.CustomUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        // securityconfig에 있는 Authentication 호출
       this.authenticationManager = authenticationManager;

        // 인자로 들어온 URL에서 정보를 보겠다.
        setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
    }

    @Override
    // login form에서 받은 로그인 요청(id와 password만 뽑아서 쓰겠다)
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // id, pw를 찾아내는 과정
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // authenticationToken(인증정보/ 유효한 토큰은 아님) 생성 -> id와 pw가 담겨있음
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        // db에 있는 정보랑 비교해서 검증(Lock여부, 활성화 여부. Expired 여부 등)
       return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    // 유효한 유저인 경우 호출됨
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication) {
        com.example.demo.security.CustomUser user = ((com.example.demo.security.CustomUser) authentication.getPrincipal());

        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // secrete key를 사용하여
        byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();

        // 정보들을 암호화한 토큰을 생성
        String token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                // 나중에 사용할 user no를 토큰에 넣음
                .setSubject("" + user.getMember().getUserNo())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .claim("rol", roles)
                .compact();

        // frontend에서는 response에 추가된 토큰을 요청 Header에 등록하고 사용.
        // 뷰로 보냈다가 axios로 헤더에 보냄
        response.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token);
    }
}