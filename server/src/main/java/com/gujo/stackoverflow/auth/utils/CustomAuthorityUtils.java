package com.gujo.stackoverflow.auth.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;

public class CustomAuthorityUtils {
    @Value("${mail.address.admin}")     // 환경변수 설정 해뒀어요
    private String adminMailAddress;

    // 관리자라서 관리자 + 사용자
    private final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");

    private final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");

    // 관리자 이메일 일치 여부 판단
    public List<GrantedAuthority> createAuthorities(String email) {
        if( email.equals(adminMailAddress)) {
            return ADMIN_ROLES;
        }
        return USER_ROLES;
    }
}
