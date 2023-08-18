package com.gujo.stackoverflow.member.service;


import com.gujo.stackoverflow.auth.utils.CustomAuthorityUtils;
import com.gujo.stackoverflow.exception.ExceptionCode;
import com.gujo.stackoverflow.exception.BusinessLogicException;

import com.gujo.stackoverflow.member.entity.Member;
import com.gujo.stackoverflow.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
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

        //중복 displayName 확인 ( 맨 아래 메서드추가 )
        checkDisplayName(member.getDisplayName());
        //중복 email 확인
        checkEmail(member.getEmail());

        // password 암호화
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles);

        Member createdMember = memberRepository.save(member);
        if (createdMember.getMemberId() < 2) {
            createdMember.setReputation(15L);
        }
        return createdMember;
    }

    public Member updateMember (Member member) {
        Member findMember = findVerifiedMember(member.getMemberId());

        checkLoginMemberWrote(findMember.getMemberId());

        if (member.getDisplayName() != null) {
            findMember.setDisplayName(member.getDisplayName());
        }
        if (member.getEmail() != null) {
            findMember.setEmail(member.getEmail());
        }
        if (member.getPassword() != null) {
            findMember.setPassword(member.getPassword());
        }

        return memberRepository.save(findMember);
    }

    @Transactional(readOnly = true)
    public Member findMember(Long memberId) {
        return findVerifiedMember(memberId);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public void deleteMember(Long memberId) {
        Member findMember = findVerifiedMember(memberId);
        checkLoginMemberWrote(findMember.getMemberId());

        memberRepository.deleteById(findMember.getMemberId());
    }

    // 중복 displayName 확인
    private void checkDisplayName(String displayName) {
        Optional<Member> member = memberRepository.findByDisplayName(displayName);
        if (member.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NAME_EXISTS);
        }
    }

//     중복 email 확인
    private void checkEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_EMAIL_EXISTS);
        }
    }

    //    검색 추가
    public Page<Member> displayNameSearchList(String displayName, Pageable pageable) {
        return memberRepository.findByDisplayNameContaining(displayName, pageable);
    }

    private Member findVerifiedMember(Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);

        if (findMember.isPresent())
            return findMember.get();

        else throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
    }

    public Member findLoginMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Optional<Member> member = memberRepository.findByEmail(authentication.getName());

            if (member.isPresent()) {
                return member.get();
            }
        }
        throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_AUTHENTICATED);
    }

//    현재 로그인한 회원 ID와 입력된 ID값 비교 후 같으면 로그인 회원 리턴
    public Member checkLoginMemberWrote(Long memberId) {
        Member loginMember = findLoginMember();
        if (!memberId.equals(loginMember.getMemberId())) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_AUTHENTICATED);
        }
        return loginMember;
    }

//    추천 혹은 비추천 시 로직
    public Member vote(Member postMember, Long score) {
        Member loginMember = findLoginMember();

        if (loginMember.getReputation() < 15) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_AUTHORIZED);
        }

        postMember.setReputation(postMember.getReputation() + score);

        if (postMember.getReputation() < 1) {
            postMember.setReputation(1L);
        }

        return postMember;
    }
}




