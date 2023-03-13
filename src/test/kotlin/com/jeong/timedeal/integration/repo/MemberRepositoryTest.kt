package com.jeong.timedeal.integration.repo
//TODO 통합테스트만 모듈로 따로 빼고 싶은데, 방법을 모르겠네

import com.jeong.timedeal.entity.Member
import com.jeong.timedeal.repo.MemberRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest

// TODO rollback 추가
@SpringBootTest
class MemberRepositoryTest(
    private val memberRepository: MemberRepository
) : StringSpec({// TODO StringSpec 외에 다른 스팩으로 어떻게 constructor 로 di 를 구현할 수 있는지 모르겠네. 어떤 개념을 모르는거지?
    "should return the member provided by member repository" {
        // given
        val expected = Member(
            role = Member.Role.USER,
            account = "new-acocunt",
            password = "new-password",
            name = "my-name",
            email = "member@mail.com",
            phone = "123456"
        )

        // when
        val id = memberRepository.save(expected).id
        val actual = memberRepository.findById(id).get()

        // then
        actual.account shouldBe expected.account
        actual.name shouldBe expected.name
        actual.email shouldBe expected.email
    }
})