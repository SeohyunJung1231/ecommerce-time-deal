package com.jeong.timedeal.service

import com.jeong.timedeal.controller.model.MemberRequest
import com.jeong.timedeal.controller.model.MemberResponse
import com.jeong.timedeal.controller.model.MemberUpdateRequest
import com.jeong.timedeal.entity.Member
import com.jeong.timedeal.repo.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    fun create(memberRequest: MemberRequest): Long {
        require(!exist(memberRequest.account)) { "account: ${memberRequest.account} already exists" }
        return memberRepository.save(
            Member(
                role = memberRequest.role,
                account = memberRequest.account,
                password = memberRequest.password,
                name = memberRequest.name,
                email = memberRequest.email,
                phone = memberRequest.phone
            )
        ).id
    }

    fun get(memberId: Long): MemberResponse {
        require(exist(memberId)) { "member with id: $memberId does not exist" }
        return memberRepository.findById(memberId).get().let { member ->
            MemberResponse(
                id = member.id, account = member.account, name = member.name, email = member.email, phone = member.phone
            )
        }
    }

    fun update(id: Long, memberUpdateRequest: MemberUpdateRequest): Long {
        val member: Member = memberRepository.findById(id).get() // TODO update 요청은 웹에서 보낼것이므로 항상 존재할것이다. 라고 가정해도 되나?
        // TODO patch 가 필요한 DTO 는 var 로 바꾸는 게 맞나?
        if (memberUpdateRequest.name != null) member.name = memberUpdateRequest.name
        if (memberUpdateRequest.email != null) member.email = memberUpdateRequest.email
        if (memberUpdateRequest.phone != null) member.phone = memberUpdateRequest.phone
        return memberRepository.save(member).id
    }

    private fun exist(account: String) = memberRepository.findByAccount(account) != null
    private fun exist(id: Long) = !memberRepository.findById(id).isEmpty

}