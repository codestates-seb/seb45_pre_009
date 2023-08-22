package com.gujo.stackoverflow.member.service;


import com.gujo.stackoverflow.answer.entity.Answer;
import com.gujo.stackoverflow.auth.utils.CustomAuthorityUtils;
import com.gujo.stackoverflow.exception.ExceptionCode;
import com.gujo.stackoverflow.exception.BusinessLogicException;

import com.gujo.stackoverflow.member.entity.Member;
import com.gujo.stackoverflow.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;

    @Value("${temp.password}")
    private String tempPassword;

    @Lazy // 순환참조
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

//        테스트용 첫 회원 15평판 부여
//        if (createdMember.getMemberId() < 2) {
//            createdMember.setReputation(15L);
//        }
        return createdMember;
    }

    //POST(OAuth2.0 회원 등록) : OAuth2.0 를 통해 가입된 회원 정보 저장 (DB에 해당 정보 존재하면 메서드 리턴하고 존재하지 않으면 저장)
    public Member createMemberOAuth2(Member member) {
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember.isPresent()) {
            return findMember.get(); //이미 DB에 저장된 정보가 있다면 반환
        }

        // DB에 저장된 정보가 없다면
        List<String> roles = authorityUtils.createRoles(member.getEmail());
        Member beSavedMember = new Member(
                member.getDisplayName(),          // DisplayName null (이후 추가로 변경하는 창을 redirection 할 수 있음)
                member.getEmail(), // 구글 이메일을 DB에 등록
                "1111",                //암호화된 비밀번호 빈 문자열
                roles               //권한 목록
        );
        beSavedMember.setPassword(passwordEncoder.encode(beSavedMember.getPassword()));

//        System.out.println("# Service : " + tempPassword);
        beSavedMember.setOauth(true);

        return memberRepository.save(beSavedMember);
    }

    public Member updateMember(Member member) {
        Member findMember = findVerifiedMember(member.getMemberId());

        checkLoginMemberHasAuthority(findMember.getMemberId());

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
        Member member = findVerifiedMember(memberId);

        if( member.getMemberStatus() == Member.MemberStatus.MEMBER_EXIST ) {
            return member;
        } else throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
    }

    public List<Member> findMembers() {
        List<Member> allMembers = memberRepository.findAll();

        return allMembers.stream()
                .filter(answer -> answer.getMemberStatus() == Member.MemberStatus.MEMBER_EXIST)
                .collect(Collectors.toList());
    }

    public void deleteMember(Long memberId) {
        Member findMember = findVerifiedMember(memberId);
        checkLoginMemberHasAuthority(findMember.getMemberId());

        findMember.setMemberStatus(Member.MemberStatus.MEMBER_NOT_EXIST);

        memberRepository.save(findMember);
//        memberRepository.deleteById(findMember.getMemberId());
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

//    admin 일 경우, 로그인한 회원 ID와 게시물을 작성한 회원 ID가 같으면 패스
    public void checkLoginMemberHasAuthority(Long wroteMemberId) {
        Member loginMember = findLoginMember();
        if (loginMember.getRoles().contains("ADMIN")) {
            return;
        }
        if (!wroteMemberId.equals(loginMember.getMemberId())) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_AUTHENTICATED);
        }
    }

//    추천 혹은 비추천 시 로직
    public Member vote(Member postMember, Long score) {
        Member loginMember = findLoginMember();

//        평판 15점 미만 시 추천 기능 사용 불가
//        if (loginMember.getReputation() < 15) {
//            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_AUTHORIZED);
//        }

        postMember.setReputation(postMember.getReputation() + score);

//        평판이 1 미만으로 내려갈 시 최소 점수 1 부여
        if (postMember.getReputation() < 1) {
            postMember.setReputation(1L);
        }

        return postMember;
    }
}




