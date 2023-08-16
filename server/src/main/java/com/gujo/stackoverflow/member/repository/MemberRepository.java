package com.gujo.stackoverflow.member.repository;

import com.gujo.stackoverflow.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByDisplayName(String displayName);
    // 로그인 디티오에 유저네임을 이메일로 로그인 할 테니 디스플레이네임은..

    Optional<Member> findByEmail(String email);
}
