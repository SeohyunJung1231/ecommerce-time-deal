package com.jeong.timedeal.service

import com.jeong.timedeal.controller.model.MemberRequest
import com.jeong.timedeal.domain.Member
import com.jeong.timedeal.repo.MemberRepository
import org.springframework.stereotype.Service

@Service

class MemberService(
    private val memberRepository: MemberRepository
) {

    fun create(memberRequest: MemberRequest): Long {
        // exist 확인
        requireNotNull(memberRepository.findByAccount(memberRequest.account)) { "account already exists" }

        // 생성
        return memberRepository.save(
            Member(
                roleId = memberRequest.role,
                account = memberRequest.account,
                name = memberRequest.name,
                email = memberRequest.email,
                phone = memberRequest.phone
            )
        ).id
    }
}