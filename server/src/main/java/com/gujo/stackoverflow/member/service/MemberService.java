package com.gujo.stackoverflow.member.service;

import com.gujo.stackoverflow.auth.utils.CustomAuthorityUtils;
import com.gujo.stackoverflow.member.entity.Member;
import com.gujo.stackoverflow.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, CustomAuthorityUtils authorityUtils) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityUtils = authorityUtils;
    }

    public Member createMember(Member member) {

        //같은이름 확인 ( 맨 아래 메서드추가 )
        checkDisplayName(member.getDisplayName());

        // password 암호화
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles);

        return memberRepository.save(member);
    }

    public Member updateMember (Member member) {
        Optional<Member> findMember = memberRepository.findById(member.getMemberId());

        if (findMember.isPresent()) {

            Member existingMember = findMember.get();

            if (member.getDisplayName() != null) {
                existingMember.setDisplayName(member.getDisplayName());
            }

            if (member.getEmail() != null) {
                existingMember.setEmail(member.getEmail());
            }

            if (member.getPassword() != null) {
                existingMember.setPassword(member.getPassword());
            }

            return memberRepository.save(existingMember);

        }
        return null;
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElse(null);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public void deleteMember(Long memberId) {
        Member findMember = findMemberById(memberId);
        if (findMember != null) {
            memberRepository.delete(findMember);
        }
    }

    // 중복 diplayName 확인
    private void checkDisplayName(String displayName) {
        Optional<Member> member = memberRepository.findByDisplayName(displayName);
        if (member.isPresent()) {
            throw new IllegalStateException("이미 존재하는 이름 입니다.");
        }
    }

    // 중복 id 확인
//    private void checkMemberExistence(Long memberId) {
//        if (memberRepository.existsById(memberId)) {
//            throw new IllegalStateException("이미 존재하는 회원 입니다.");
//        }
//    }

    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElse(null);
    }
}




