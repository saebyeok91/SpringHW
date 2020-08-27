package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

// import static org.hibernate.internal.util.StringHelper.isNotEmpty;
// import static org.springframework.util.ObjectUtils.isEmpty;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private static final Logger log = LoggerFactory.getLogger(com.example.demo.security.JwtAuthorizationFilter.class);

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    // JwtAuthenticationFilter에서 오는 정보를 받음.
    // header를 parsing하여 JWT Token을 확인, parsing 및 검증.
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        Authentication authentication = getAuthentication(req);
        String header = req.getHeader(SecurityConstants.TOKEN_HEADER);

        // HEADER가 비어있으면/ 토큰 프리픽스가 있으면 필터로
        if(isEmpty(header) || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    private boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    // 토큰은 세부분으로 나눠짐 첫번째 String (header), 두번째 String (payload), 3번째 String (Digest)
    // -> Header - 추가 정보 어떤 알고리즘으로 암호화가 되는지
    // -> Payload(Body) - 내가 설정한 정보들(subject, user no...), 시간정보
    // -> Signature - hash512로 암호화 시켜주고 있는 부분

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
        String token = req.getHeader(SecurityConstants.TOKEN_HEADER);

        if (isNotEmpty(token)) {
            try {
                // 토큰이 NULL이 아니니까 get bytes로 sigining key를 가져옴
                byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();

                Jws<Claims> parsedToken = Jwts.parser()
                        .setSigningKey(signingKey)
                        // bearer를 빼고 암호화를 진행
                        .parseClaimsJws(token.replace("Bearer", ""));

                String username = parsedToken
                        .getBody()
                        .getSubject();

                List<SimpleGrantedAuthority> authorities = ((List<?>) parsedToken.getBody()
                        .get("rol")).stream()
                        .map(auth -> new SimpleGrantedAuthority((String) auth))
                        .collect(Collectors.toList());

                // username이 비어있지 않으면 토큰을 리턴
                if(isNotEmpty(username)) {
                    return new UsernamePasswordAuthenticationToken(
                            username, null, authorities
                    );
                }
            } catch (ExpiredJwtException e) {
                log.warn("Request: parse expired JWT - {} failed: {}",
                        token, e.getMessage());
            } catch (UnsupportedJwtException e) {
                log.warn("Request: parse unsupported JWT - {} failed: {}",
                        token, e.getMessage());
            } catch (MalformedJwtException e) {
                log.warn("Request: parse invalid JWT - {} failed: {}",
                        token, e.getMessage());
            } catch (SignatureException e) {
                log.warn("Request: parse JWT with invalid signature - {} failed: {}",
                        token, e.getMessage());
            } catch (IllegalArgumentException e) {
                log.warn("Request: parse empty or NULL - {} failed: {}",
                        token, e.getMessage());
            }
        }

        return null;
    }
}