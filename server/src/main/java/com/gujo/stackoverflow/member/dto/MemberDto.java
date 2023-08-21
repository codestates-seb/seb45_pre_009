package com.gujo.stackoverflow.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;
import java.util.List;


public class MemberDto {

    @Setter
    @Getter
    @ApiModel(value = "회원", description = "회원 diplayName, Email, Password")
    public static class MemberPostDto {  //API 문서화시 questionDto 부분에서 postDto명이 중복코드로 MemberPostDto로 변경

        @NotNull(message = "이름을 입력해 주세요")
        @ApiModelProperty(example="김코딩", required=true)
        private String displayName;

        @Email
        @NotNull(message = "email을 입력해 주세요")
        @ApiModelProperty(example = "asd@asd.asd", required = true)
        private String email;

        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$",
                 message = "최소 8글자, 최대 16글자, 숫자, 특수문자가 최소 1개 포함되어야 합니다")
        @NotNull(message = "비밀번호를 입력해 주세요")
        @ApiModelProperty(example = "qwerasdf1!", required = true)
        private String password;
    }

    @Getter
    @Setter
    @ApiModel(value = "OAuth", description = "회원 diplayName, Email")
    public static class OauthPostDto {

        @ApiModelProperty(example="박코드", required=true)
        private String displayName;

        @ApiModelProperty(example = "qwe@qwe.qwe", required = true)
        private String email;

        private String password;
    }

    @Setter
    @Getter
    @ApiModel(value = "회원 수정", description = "회원 diplayName, Email, Password")
    public static class MemberPatchDto { //API 문서화시 questionDto 부분에서 patchDto명이 중복코드로 MemberPatchDto로 변경

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

//        private String password;

        private boolean oauth;

        private List<String> roles;

    }

}
