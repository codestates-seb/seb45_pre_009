package com.gujo.stackoverflow.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;
import java.util.List;

public class MemberDto {

    @Setter
    @Getter
    public static class PostDto {

        @NotNull(message = "이름을 입력해 주세요")
        private String displayName;

        @Email
        @NotNull(message = "email을 입력해 주세요")
        private String email;

        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$",
                 message = "최소 8글자, 최대 16글자, 숫자, 특수문자가 최소 1개 포함되어야 합니다")
        @NotNull(message = "비밀번호를 입력해 주세요")
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

        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$",
                message = "최소 8글자, 최대 16글자, 숫자, 특수문자가 최소 1개 포함되어야 합니다")
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

        private List<String> roles;

    }

}
