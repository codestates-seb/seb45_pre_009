package com.gujo.stackoverflow.question.entity;

import com.gujo.stackoverflow.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private Long point;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = true)
    private LocalDateTime modifiedAt;
}
