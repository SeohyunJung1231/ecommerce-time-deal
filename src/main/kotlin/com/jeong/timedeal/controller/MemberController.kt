package com.jeong.timedeal.controller

import com.jeong.timedeal.controller.model.MemberRequest
import com.jeong.timedeal.service.MemberService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/members")
class MemberController(
    private val memberService: MemberService
) {
    @PostMapping
    @Operation(summary = "회원 가입 요청", description = "해당 API 호출시, 회원 가입됩니다")
    fun register(@RequestBody member: MemberRequest): Long {
        return memberService.create(member)
    }
}