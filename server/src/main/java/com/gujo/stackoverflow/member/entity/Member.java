package com.gujo.stackoverflow.member.entity;

import com.gujo.stackoverflow.answer.entity.Answer;
import com.gujo.stackoverflow.comment.entity.Comment;
import com.gujo.stackoverflow.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, length = 20)
    private String displayName;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 100)  // 암호화 하면 길이가 길어져서 수정했어요!
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)     // 사용자 등록 시, 사용자 권한 등록하는 권한 테이블 생성
    private List<String> roles = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = true)
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "member")
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @Column(nullable = false)
    private boolean oauth;
  
    @Column(nullable = false)
    private Long reputation = 1L;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Member.MemberStatus memberStatus = Member.MemberStatus.MEMBER_EXIST;

    public enum MemberStatus {
        MEMBER_EXIST("존재하는 회원"),
        MEMBER_NOT_EXIST("존재하지 않는 회원");

        private String status;

        MemberStatus(String status) {
            this.status = status;
        }
    }

    public Member(String email) {
        this.email = email;
    }

    public Member(String displayName, String email, String password, List<String> roles) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public boolean getOauth() {
        return this.oauth;
    }
}