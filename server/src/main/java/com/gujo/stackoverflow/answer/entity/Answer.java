package com.gujo.stackoverflow.answer.entity;

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

    @Column(nullable = false)
    private Long point = 0L;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = true)
    private LocalDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

//    // setQuestion 대신하는 느낌이었는데 기억이 잘 안 나가지구 .,
//    public void addQuestion(Question question) {
//        this.question = question;
//    }

}