package com.jeong.timedeal.controller

import com.jeong.timedeal.controller.model.MemberRequest
import com.jeong.timedeal.controller.model.MemberUpdateRequest
import com.jeong.timedeal.repo.MemberRepository
import com.jeong.timedeal.service.MemberService
import io.swagger.v3.oas.annotations.Operation
import jakarta.transaction.Transactional
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/members")
class MemberController(
    private val memberService: MemberService,
    private val memberRepository: MemberRepository
) {
    @PostMapping
    @Operation(summary = "회원 가입 요청", description = "해당 API 호출시, 회원 가입됩니다")
    fun register(@RequestBody member: MemberRequest): Long {
        return memberService.create(member)
    }
    @PatchMapping("/{id}")
    @Operation(summary = "회원 수정 요청", description = "해당 API 호출시, 회원 정보가 수정됩니다")
    fun modify(@PathVariable id: Long, @RequestBody member: MemberUpdateRequest): Long {
        return memberService.update(id, member)
    }

    @DeleteMapping("/{id}")
    @Transactional // TODO modifying?
    @Operation(summary = "회원 탈퇴 요청", description = "해당 API 호출시, 회원 탈퇴됩니다")
    fun resign(@PathVariable id: Long): Long {
        memberRepository.deleteById(id)
        return id
    }
}