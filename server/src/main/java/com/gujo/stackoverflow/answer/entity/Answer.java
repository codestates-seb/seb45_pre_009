package com.gujo.stackoverflow.answer.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Answer {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long answerId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int point;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime modifiedAt;
}