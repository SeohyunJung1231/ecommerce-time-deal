package com.jeong.timedeal.service

import com.jeong.timedeal.controller.model.OrderSheetResponse
import com.jeong.timedeal.controller.model.PurchasedProductResponse
import com.jeong.timedeal.entity.Member
import com.jeong.timedeal.entity.MemberProduct
import com.jeong.timedeal.entity.Product
import com.jeong.timedeal.repo.MemberProductRepository
import com.jeong.timedeal.repo.MemberRepository
import com.jeong.timedeal.repo.ProductRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PurchaseService(
    private val memberProductRepository: MemberProductRepository,
    private val memberRepository: MemberRepository,
    private val productRepository: ProductRepository
) {
    fun purchase(memberId: Long, productId: Long): OrderSheetResponse {
        val member: Member = memberRepository.findById(memberId).get()
        val product: Product = productRepository.findById(productId).get()
        // TODO 권한쪽 구현되면, ADMIN 회원이 아닌 경우 에러처리 한다
//        if (member.role != Member.Role.USER) throw AccessDeniedException()

        memberProductRepository.save(
            MemberProduct(
                buyer = member, product = product, purchaseTime = LocalDateTime.now()
            )
        )
        return OrderSheetResponse(memberId = memberId, productId = productId)
    }

    fun fetchAllPurchasedBy(memberId: Long): List<PurchasedProductResponse> {
        val memberProducts = memberProductRepository.findAllByBuyerId(memberId) ?: listOf()
        return memberProducts.map {
            PurchasedProductResponse(
                id = it.product.id,
                name = it.product.name,
                price = it.product.price,
                purchaseTime = it.purchaseTime
            )
        }
    }
}