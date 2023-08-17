package com.gujo.stackoverflow.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MemberAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    // 인증 성공 시
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // 인증 성공 후, 로그 기록
        log.info("🌟🌟🌟 Authenticated Successfully 🌟🌟🌟");



        // 로그인 인증 성공하면 홈화면으로 가는 등 머가 더 있어야 할 거 같아요 ...
    }
}
