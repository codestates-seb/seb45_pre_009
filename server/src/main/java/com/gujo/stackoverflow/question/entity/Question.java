package com.gujo.stackoverflow.question.entity;


import com.gujo.stackoverflow.answer.entity.Answer;
import com.gujo.stackoverflow.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @ManyToOne // 유저 한명이 여러 질문 작성 가능 -> N:1
    @JoinColumn(name = "MEMBER_ID") // 현재 테이블에서 외래키에 해당하는 컬럼의 이름
    private Member member;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long point = 0L; // 초기값 설정

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = true)
    private LocalDateTime modifiedAt;

    @Column(nullable = false)
    private Long view = 0L;

//    1:N Answer와 양방향 매핑용
//    mappedBy 값은 매핑하는 테이블에서 FK에 해당하는 필드
//    @OneToMany(mappedBy = "question")
//    private List<Answer> answers = new ArrayList<>();
}
