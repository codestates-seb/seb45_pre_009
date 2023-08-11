package com.gujo.stackoverflow.member.Controller;

import com.gujo.stackoverflow.member.dto.MemberDto;
import com.gujo.stackoverflow.member.entity.Member;
import com.gujo.stackoverflow.member.mapper.MemberMapper;
import com.gujo.stackoverflow.member.service.MemberService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RequestMapping("/members")
@RestController
@Validated
public class MemberController {

    private final MemberService memberService;

    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping()
    public ResponseEntity postMember(@RequestBody MemberDto.postDto postDto) {

        Member member = mapper.postDtoToMember(postDto);
        Member created = memberService.createMember(member);

        MemberDto.responseDto responseDto = mapper.memberToResponseDto(created);

        return new ResponseEntity(responseDto, HttpStatus.CREATED);

    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive Long memberId, @Valid @RequestBody MemberDto.patchDto patchDto) {

        patchDto.setMemberId(memberId);

        Member response = memberService.updateMember(mapper.patchDtoToMember(patchDto));

        return new ResponseEntity<>(mapper.memberToResponseDto(response), HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive Long memberId) {

        Member response = memberService.findMember(memberId);

        return new ResponseEntity<>(mapper.memberToResponseDto(response), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getMembers() {

        List<Member> members = memberService.findMembers();
        List<MemberDto.responseDto> response = mapper.membersToResponseDtos(members);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive Long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
