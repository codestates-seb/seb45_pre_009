package com.gujo.stackoverflow.answer.entity;

import com.gujo.stackoverflow.member.entity.Member;
import com.gujo.stackoverflow.question.entity.Question;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Answer {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long answerId;

    @Column(nullable = false)
    private String content;

    // 추천수
    @Column(nullable = false)
    private Long point = 0L;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = true)
    private LocalDateTime modifiedAt;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private AnswerStatus answerStatus = AnswerStatus.ANSWER_POST;

    public enum AnswerStatus {
        ANSWER_POST(1, "게시 중"),
        ANSWER_DELETED_POST(2, "삭제 처리");

        private int stepNumber;
        private String stepDescription;

        AnswerStatus(int stepNumber, String stepDescription) {
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
    }

    @ManyToOne  // N : 1
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne  // N : 1
    @JoinColumn(name = "QUESTION_ID")
    private Question question;


    // 양방향 매핑 ( 객체 탐색)
    public void addMember(Member member) {
        this.member = member;
    }

    public void addQuestion(Question question) {
        this.question = question;
    }
}