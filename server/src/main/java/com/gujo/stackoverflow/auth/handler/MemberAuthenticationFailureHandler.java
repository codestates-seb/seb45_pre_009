package com.gujo.stackoverflow.auth.handler;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MemberAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        log.error("🚨🚨🚨 Authentication failed : {} 🚨🚨🚨", exception.getMessage());

//        sendErrorResponse(response);
    }

//    예외처리는 마지막에 할게요......
//    private void sendErrorResponse(HttpServletResponse response) throws IOException {
//        Gson gson = new Gson();
//        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.UNAUTHORIZED);
//
//    }
}
