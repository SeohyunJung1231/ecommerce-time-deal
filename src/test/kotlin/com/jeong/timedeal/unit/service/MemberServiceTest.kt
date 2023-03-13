package com.jeong.timedeal.unit.service

import com.jeong.timedeal.controller.model.MemberRequest
import com.jeong.timedeal.controller.model.MemberResponse
import com.jeong.timedeal.entity.Member
import com.jeong.timedeal.repo.MemberRepository
import com.jeong.timedeal.service.MemberService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class MemberServiceTest : BehaviorSpec({
    val repository = mockk<MemberRepository>()
    val service = MemberService(repository)

    given("맴버 가입 요청") {
        val account = "account"
        val newMemberRequest =
            MemberRequest(account = account, role = Member.Role.USER, password = "newPassword", email = "newEmail")

        `when`("이미 계정이 존재하는 맴버라면") {
            val existedMember = Member(account = account, role = Member.Role.USER, password = "password")
            every { repository.findByAccount(any()) } returns existedMember
            then("이미 존재하는 계정이라고 예외를 발생한다") {
                shouldThrow<IllegalArgumentException> { service.create(newMemberRequest) }
            }
        }

        `when`("처음 가입한 맴버라면") {
            val memberId = 1L
            every { repository.findByAccount(any()) } returns null
            every { repository.save(any()) } returns Member(
                id = memberId, role = Member.Role.USER, account = "", password = ""
            )
            then("가입이 성공적으로 된다") {
                service.create(newMemberRequest) shouldBe memberId
            }
        }
    }
    given("맴버 조회 요청") {
        val memberId = 1L
        `when`("존재하는 맴버라면") {
            val member = Member(
                id = memberId, role = Member.Role.USER, account = "account", password = "password"
            )
            every { repository.findById(any()).get() } returns member
            every { repository.findById(any()).isEmpty } returns false
            then("이미 존재하는 맴버 정보를 반환한다") {
                service.get(memberId) shouldBe MemberResponse(
                    id = member.id, account = member.account
                )
            }
        }
        `when`("존재하지 않는 맴버라면") {
            every { repository.findById(any()).isEmpty } returns true
            then("존재하지 않는 맴버라고 예외를 발생시킨다") {
                shouldThrow<IllegalArgumentException> { service.get(memberId) }

            }
        }
    }
})