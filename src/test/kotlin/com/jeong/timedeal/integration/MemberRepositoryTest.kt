package com.jeong.timedeal.integration
//TODO 통합테스트만 모듈로 따로 빼고 싶은데, 방법을 모르겠네

import com.jeong.timedeal.entity.Member
import com.jeong.timedeal.repo.MemberRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberRepositoryTest(

) : BehaviorSpec({

    private val memberRepository: MemberRepository
    given("member 테이블 crud 확인") {
        `when`("데이터 저장") {
            val member = Member(
                id = 1L,
                role = Member.Role.USER,
                account = "new-acocunt",
                password = "new-password",
                name = "my-name",
                email = "member@mail.com",
                phone = "123456"
            )
            memberRepository.save(member)
            then("데이터가 제대로 저장되었다") {
                memberRepository.findById(member.id) shouldBe member
            }
        }
//        `when`("ff") {
//            then("ff") {
//
//            }
//        }
    }


})