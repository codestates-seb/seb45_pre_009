package com.gujo.stackoverflow.member.mapper;

import com.gujo.stackoverflow.member.dto.MemberDto;
import com.gujo.stackoverflow.member.entity.Member;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member postDtoToMember(MemberDto.MemberPostDto memberPostDto);

    Member oauthPostDtoToMember(MemberDto.OauthPostDto postDto);

    Member patchDtoToMember(MemberDto.MemberPatchDto memberPatchDto);

    MemberDto.ResponseDto memberToResponseDto(Member member);

    List<MemberDto.ResponseDto> membersToResponseDtos(List<Member> members);

}


