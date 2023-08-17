package com.gujo.stackoverflow.exception;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class ExceptionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @Column(nullable = false)
    private String exception;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    public ExceptionLog(String exception, String message) {
        this.exception = exception;
        this.message = message;
    }

    public ExceptionLog() {

    }
}
