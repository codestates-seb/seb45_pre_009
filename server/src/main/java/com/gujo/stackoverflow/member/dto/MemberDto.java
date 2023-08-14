package com.gujo.stackoverflow.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;

public class MemberDto {

    @Setter
    @Getter
    public static class PostDto {

        private String displayName;

        private String email;

        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$",
                 message = "최소 8글자, 최대 16글자, 글자 1개, 숫자 1개, 특수문자 1개")
        @NotNull
        private String password;
    }

    @Getter
    @Setter
    public static class OauthPostDto {

        private String displayName;

        private String email;
    }

    @Setter
    @Getter
    public static class PatchDto {

        private Long memberId;

        private String displayName;

        private String email;

        private String password;

    }

    @Getter
    @Setter
    public static class ResponseDto {

        private Long memberId;

        private String email;

        private String displayName;

        private String password;

        private boolean oauth;

    }

}
