package com.jeong.timedeal.integration.repo

import com.jeong.timedeal.entity.Member
import com.jeong.timedeal.entity.MemberProduct
import com.jeong.timedeal.entity.Product
import com.jeong.timedeal.entity.SaleTime
import com.jeong.timedeal.repo.MemberProductRepository
import com.jeong.timedeal.repo.MemberRepository
import com.jeong.timedeal.repo.ProductRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class MemberProductRepositoryTest(
    private val memberProductRepository: MemberProductRepository,
    private val memberRepository: MemberRepository,
    private val productRepository: ProductRepository
) : StringSpec({
    "should return memberProduct provided by memberProduct repository" {
        // given
        val seller = Member(
            role = Member.Role.ADMIN,
            account = "seller",
            password = "seller-password",
            name = "my-seller",
            email = "seller@mail.com",
            phone = "123456"
        )
        val buyer = Member(
            role = Member.Role.USER,
            account = "buyer",
            password = "buyer-password",
            name = "my-buyer",
            email = "buyer@mail.com",
            phone = "123456"
        )
        val sellerId = memberRepository.save(seller).id
        val buyerId = memberRepository.save(buyer).id

        val product = Product(
            sellerId = sellerId,
            saleTime = SaleTime(saleStartAt = LocalDateTime.now(), saleEndAt = LocalDateTime.now()),
            name = "cup",
            price = 10_000L,
            stock = 10L
        )
        val productId = productRepository.save(product).id


        // when
        val expected = MemberProduct(
            buyer = buyer,
            product = product,
            purchaseTime = LocalDateTime.now()
        )
        val actual = memberProductRepository.save(expected)


        // then
        actual.product.name shouldBe expected.product.name
        actual.product.price shouldBe expected.product.price
    }
})