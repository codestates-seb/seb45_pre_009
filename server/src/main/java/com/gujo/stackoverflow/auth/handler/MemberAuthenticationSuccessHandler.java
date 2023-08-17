package com.gujo.stackoverflow.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gujo.stackoverflow.exception.BusinessLogicException;
import com.gujo.stackoverflow.exception.ExceptionCode;
import com.gujo.stackoverflow.member.entity.Member;
import com.gujo.stackoverflow.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

@Slf4j
public class MemberAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final MemberRepository memberRepository;

    public MemberAuthenticationSuccessHandler(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 인증 성공 시
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // 인증 성공 후, 로그 기록
        log.info("🌟🌟🌟 Authenticated Successfully 🌟🌟🌟");

        ObjectMapper objectMapper = new ObjectMapper();

        String username = authentication.getName();
        Optional<Member> optionalMember = memberRepository.findByEmail(username);
        Member member = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        HashMap<String, Long> map = new HashMap<>();
        map.put("memberId", member.getMemberId());

        String jsonResponse = objectMapper.writeValueAsString(map);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);

        // 로그인 인증 성공하면 홈화면으로 가는 등 머가 더 있어야 할 거 같아요 ...
    }
}
