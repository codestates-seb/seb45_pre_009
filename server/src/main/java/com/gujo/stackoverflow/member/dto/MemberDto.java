package com.gujo.stackoverflow.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class MemberDto {

    @Setter
    @Getter
    public class postDto {

        private String displayName;
        private String email;
        private String password;
    }

    @Setter
    @Getter
    public class patchDto {

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
