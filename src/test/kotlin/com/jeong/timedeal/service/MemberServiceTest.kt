package com.jeong.timedeal.service

import com.jeong.timedeal.controller.model.MemberRequest
import com.jeong.timedeal.entity.Member
import com.jeong.timedeal.repo.MemberRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.lang.IllegalArgumentException

class MemberServiceTest : BehaviorSpec({
    val repository = mockk<MemberRepository>()
    val service = MemberService(repository)

    given("맴버 가입 요청") {
        val account = "account"
        val newMemberRequest =
            MemberRequest(account = account, role = Member.Type.USER, password = "newPassword", email = "newEmail")

        `when`("처음 가입한 맴버라면") {
            //TODO kotest 의 arbs 로 구현
            val existedMember = Member(account = account, roleId = Member.Type.USER, password = "password")
            every { repository.findByAccount(any()) } returns existedMember
            then("이미 존재하는 계정이라고 예외를 발생한다") {
                shouldThrow<IllegalArgumentException> { service.create(newMemberRequest) }
            }
        }

        `when`("이미 계정이 존재하는 맴버라면") {
            every { repository.findByAccount(any()) } returns null
            every { repository.save(any()) } returns Member(
                id = 1L,
                roleId = Member.Type.USER,
                account = "",
                password = ""
            )
            then("가입이 성공적으로 된다") {
                service.create(newMemberRequest) shouldBe 1L
            }
        }
    }
})