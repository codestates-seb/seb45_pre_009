package com.gujo.stackoverflow.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

public class MemberDto {

    @Setter
    @Getter
    public static class postDto {

        private String displayName;

        private String email;

        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$",
                 message = "최소 8글자, 최대 16글자, 글자 1개, 숫자 1개, 특수문자 1개")
        private String password;
    }

    @Setter
    @Getter
    public static class patchDto {

        private Long memberId;

        private String displayName;

        private String email;

        private String password;

    }

    @Getter
    @Setter
    public static class responseDto{

        private Long memberId;

        private String email;

        private String displayName;

        private String password;

    }

}
