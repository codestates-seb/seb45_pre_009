package com.gujo.stackoverflow.auth.filter;

import com.gujo.stackoverflow.auth.jwt.JwtTokenizer;
import com.gujo.stackoverflow.auth.utils.CustomAuthorityUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

// request header 에 포함된 JWT 검증작업
public class JwtVerificationFilter extends OncePerRequestFilter {
                                            // ^ request 당 한 번만 실행되는 필터
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;      // JWT 검증 성공 시 Authentication 객체에 채울 사용자 권한 생성

    public JwtVerificationFilter(JwtTokenizer jwtTokenizer, CustomAuthorityUtils authorityUtils) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Map<String, Object> claims = verifyJws(request); // JWT 검증
        setAuthenticationToContext(claims); // Authentication 객체를 SecurityContext 에 저장

        filterChain.doFilter(request, response);    // 다음 Security Filter 호출
    }


    // 특정 조건에 부합하면( true 면) 해당 filter 동작 X
    //               ==> Authorization header 값이 null 이거나 Bearer 로 시작하지 않으면 이 필터 동작 안 함
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        return authorization == null || !authorization.startsWith("Bearer");
    }

    // JWT 검증 메서드
    private Map<String, Object> verifyJws(HttpServletRequest request) {
        String jws = request.getHeader("Authorization").replace("Bearer ", "");

        // JWT 서명 검증하기 위한 SecretKey 얻기
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        // JWT 에서 Claims 파싱 ( 내부적으로 서명 검증에 성공했다는 의미)
        Map<String, Object> claims = jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody();

        return claims;
    }

    // Authentication 객체를 SecurityContext 에 저장
    private void setAuthenticationToContext(Map<String, Object> claims) {
        // JWT 에서 파싱한 Claims 에서 username 얻기
        String username = (String) claims.get("username");

        // Claims 에서 얻은 권한 정보를 기반으로 authorities 생성
        List<GrantedAuthority> authorities = authorityUtils.createAuthorities((List)claims.get("roles"));

        // 위 정보( username, List<GrantedAuthority) 포함한 Authentication 객체 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
