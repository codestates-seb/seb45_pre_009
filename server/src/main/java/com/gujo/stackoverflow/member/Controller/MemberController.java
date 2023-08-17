package com.gujo.stackoverflow.member.Controller;

import com.gujo.stackoverflow.member.dto.MemberDto;
import com.gujo.stackoverflow.member.entity.Member;
import com.gujo.stackoverflow.member.mapper.MemberMapper;
import com.gujo.stackoverflow.member.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import java.util.List;

/**
 *  @ApiOperation 추가 API문서화
 */
@RequestMapping("/members")
@RestController
@Validated
@Api(tags = "회원 API")
public class MemberController {

    private final MemberService memberService;

    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping()
    @ApiOperation(value = "회원 가입", notes = "회원 가입 기능입니다.")
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.PostDto postDto) {

        Member member = mapper.postDtoToMember(postDto);
        member.setOauth(false);
        Member created = memberService.createMember(member);

        MemberDto.ResponseDto responseDto = mapper.memberToResponseDto(created);

        return new ResponseEntity(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/oauth")
    public ResponseEntity postOauthMember(@RequestBody MemberDto.OauthPostDto postDto) {

        Member member = mapper.oauthPostDtoToMember(postDto);
        member.setOauth(true);
        Member created = memberService.createMember(member);

        MemberDto.ResponseDto responseDto = mapper.memberToResponseDto(created);

        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{member-id}")
    @ApiOperation(value = "회원 정보 수정", notes = "회원 회원 정보를 수정할 수 있습니다.")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive Long memberId, @Valid @RequestBody MemberDto.PatchDto patchDto) {

        patchDto.setMemberId(memberId);

        Member response = memberService.updateMember(mapper.patchDtoToMember(patchDto));

        return new ResponseEntity<>(mapper.memberToResponseDto(response), HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    @ApiOperation(value = "회원 상세 조회", notes = "회원 번호로 상세 정보를 조회할 수 있습니다.")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive Long memberId) {

        Member response = memberService.findMember(memberId);

        return new ResponseEntity<>(mapper.memberToResponseDto(response), HttpStatus.OK);
    }


    // pagenation 구현
    @GetMapping()
    @ApiOperation(value = "회원 목록 조회", notes = "여러 회원 목록을 조회할 수 있습니다.")
    public ResponseEntity<List<MemberDto.ResponseDto>> getMembers(Pageable pageable) {

        List<Member> members = memberService.findMembers();
        List<MemberDto.ResponseDto> response = mapper.membersToResponseDtos(members);

        return new ResponseEntity<>(response, HttpStatus.OK);


    }

    //    회원 displayName 검색
    @GetMapping("/search/members")
    public ResponseEntity<Page<MemberDto.ResponseDto>> searchMembers(@PageableDefault Pageable pageable,
                                                                     @RequestParam(required = false, defaultValue = "") String keyword) {
        Page<Member> searchResult = memberService.displayNameSearchList(keyword, pageable);
        Page<MemberDto.ResponseDto> responsePage = searchResult.map(
                member -> mapper.memberToResponseDto(member));
        return ResponseEntity.ok(responsePage);
    }

    @DeleteMapping("/{member-id}")
    @ApiOperation(value = "회원 삭제", notes = "회원 번호로 상세 정보를 삭제할 수 있습니다.")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive Long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
