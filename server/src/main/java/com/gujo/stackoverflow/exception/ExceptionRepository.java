package com.gujo.stackoverflow.exception;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExceptionRepository extends JpaRepository<ExceptionLog, Long> {
}
