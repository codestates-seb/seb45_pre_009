package com.gujo.stackoverflow.user.repository;

import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserId;

    @Column(nullable = false, length = 20)
    private String DisplayName;

    @Column(nullable = false, length = 50)
    private String Email;

    @Column(nullable = false, length = 20)
    private String Password;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime modifiedAt;
}
