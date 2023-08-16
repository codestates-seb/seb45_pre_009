package com.gujo.stackoverflow.member.service;

import com.gujo.stackoverflow.member.entity.Member;
import com.gujo.stackoverflow.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

        // 예외처리전 임시 메세지출력.
        System.out.println("회원이 존재하지 않습니다.");
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

    // 중복 displayName 확인
    private void checkDisplayName(String displayName) {
        Optional<Member> member = memberRepository.findByDisplayName(displayName);
        if (member.isPresent()) {
            throw new IllegalStateException("이미 존재하는 이름 입니다.");
        }
    }

    // 중복 email 확인
    private void checkEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            throw new IllegalStateException("이미 존재하는 Email 입니다.");
        }
    }

    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElse(null);
    }
}




