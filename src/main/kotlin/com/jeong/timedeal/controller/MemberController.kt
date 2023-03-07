package com.jeong.timedeal.controller

import com.jeong.timedeal.controller.model.MemberRequest
import com.jeong.timedeal.service.MemberService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberService: MemberService
) {
    @PostMapping("/members")
    fun create(@RequestBody member: MemberRequest) {
        println("hi")
    }
}