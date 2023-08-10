package com.gujo.stackoverflow.member.mapper;

import com.gujo.stackoverflow.member.dto.MemberDto;
import com.gujo.stackoverflow.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member postDtoToMember(MemberDto.postDto postDto);

    Member patchDtoToMember(MemberDto.patchDto patchDto);

    @Mapping(target = "displayName", source = "displayName")
    MemberDto.responseDto memberToResponseDto(Member member);

    List<MemberDto.responseDto> membersToResponseDtos(List<Member> members);

}


