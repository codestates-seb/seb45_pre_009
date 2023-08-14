package com.gujo.stackoverflow.member.entity;

import com.gujo.stackoverflow.answer.entity.Answer;
import com.gujo.stackoverflow.question.entity.Question;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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

//    @ManyToOne
//    @JoinColumn(name = "QUESTION_ID")
//    private Question question;
//
//    @ManyToOne
//    @JoinColumn(name = "ANSWER_ID")
//    private Answer answer;

}