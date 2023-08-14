package com.gujo.stackoverflow.member.service;

import com.gujo.stackoverflow.exception.ExceptionCode;
import com.gujo.stackoverflow.exception.BusinessLogicException;
import com.gujo.stackoverflow.member.entity.Member;
import com.gujo.stackoverflow.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member) {

        //중복 displayName 확인 ( 맨 아래 메서드추가 )
        checkDisplayName(member.getDisplayName());

        //중복 email 확인
        checkEmail(member.getEmail());


        return memberRepository.save(member);
    }

    public Member updateMember (Member member) {
        Member findMember = findVerifiedMember(member.getMemberId());

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

    private Member findVerifiedMember(Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);

        if (findMember.isPresent())
            return findMember.get();

        else throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
    }
}




