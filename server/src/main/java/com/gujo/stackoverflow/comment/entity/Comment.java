package com.gujo.stackoverflow.comment.entity;

import com.gujo.stackoverflow.answer.entity.Answer;
import com.gujo.stackoverflow.member.entity.Member;
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

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

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

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Comment.CommentStatus commentStatus = Comment.CommentStatus.COMMENT_EXIST;

    public enum CommentStatus {
        COMMENT_EXIST("존재하는 댓글"),
        COMMENT_NOT_EXIST("존재하지 않는 댓글");

        private String status;

        CommentStatus(String status) {
            this.status = status;
        }
    }
}
