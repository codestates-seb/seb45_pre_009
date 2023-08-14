package com.gujo.stackoverflow.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public class MemberDto {

    @Setter
    @Getter
    public static class PostDto {

        private String displayName;

        private String email;

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
