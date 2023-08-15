package com.gujo.stackoverflow.comment.entity;

import com.gujo.stackoverflow.answer.entity.Answer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

//    Answer와 N:1 연관관계 매핑
    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long point = 0L;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = true)
    private LocalDateTime modifiedAt;
}
