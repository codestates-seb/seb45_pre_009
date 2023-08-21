package com.gujo.stackoverflow.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gujo.stackoverflow.auth.dto.LoginDto;
import com.gujo.stackoverflow.auth.jwt.JwtTokenizer;
import com.gujo.stackoverflow.member.entity.Member;
import com.gujo.stackoverflow.member.repository.MemberRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// 로그인 인증 요청 처리하는 엔트리 포인트
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final MemberRepository memberRepository;

    @Value("${OAUTH_TEMP_PASSWORD}")
    private String tempPassword;

    private final AuthenticationManager authenticationManager;
    // ^ 얘 DI 해서 Manager ( -> provider) -> MemberDetailsService
    private final JwtTokenizer jwtTokenizer;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenizer jwtTokenizer, MemberRepository memberRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenizer = jwtTokenizer;
        this.memberRepository = memberRepository;
    }

    @SneakyThrows // 명시적 예외 처리 생략 가능, 안 쓰면 귀찮아짐~
    @Override
    // 내부에서 인증 시도하는 메서드
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        ObjectMapper objectMapper = new ObjectMapper();     // ObjectMapper : Dto 클래스로 역직렬화 ( json -> dto)
        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);
        Member member = memberRepository.findByEmail(loginDto.getUsername()).orElseThrow();
        if (member.getOauth()) {
            loginDto.setPassword(tempPassword);
        }

        // username, pw 정보 포함한 토큰 생성 ( 인증 전임)
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        // 위 토큰을 Manager 한테 전달하면서 인증 처리 위임
        return authenticationManager.authenticate(authenticationToken);
    }

    // 여기까지( 위 메서드) 오면 나머지는 내부에서 알아서 함~


    // 인증 성공시 호출
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws ServletException, IOException {    // < 얜 인증 됐음
                                                // ^ 아까 미인증 상태였던 UsernamePasswordAuthenticationToken 의 업캐스팅
        Member member = (Member) authResult.getPrincipal();     // 인증된 정보를 통해서 객체를 얻을 수 있음

        String accessToken = delegateAccessToken(member);
        String refreshToken = delegateRefreshToken(member);

                                                        // 우리는 JWT 토큰 사용한다고
        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("Refresh", refreshToken);

        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
    }

    private String delegateAccessToken(Member member) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", member.getEmail());
        claims.put("roles", member.getRoles());

        String subject = member.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    private String delegateRefreshToken(Member member) {
        String subject = member.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }
}
