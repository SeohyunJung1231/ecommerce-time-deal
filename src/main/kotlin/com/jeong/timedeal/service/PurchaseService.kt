package com.jeong.timedeal.service

import com.jeong.timedeal.controller.model.OrderSheetResponse
import com.jeong.timedeal.controller.model.ProductResponse
import com.jeong.timedeal.entity.Member
import com.jeong.timedeal.entity.MemberProduct
import com.jeong.timedeal.repo.MemberProductRepository
import com.jeong.timedeal.repo.MemberRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PurchaseService(
    private val memberProductRepository: MemberProductRepository,
    private val memberRepository: MemberRepository
) {
    fun purchase(memberId: Long, productId: Long): OrderSheetResponse {
        val member: Member = memberRepository.findById(memberId).get()
        // TODO 권한쪽 구현되면, ADMIN 회원이 아닌 경우 에러처리 한다
//        if (member.role != Member.Role.USER) throw AccessDeniedException()

        memberProductRepository.save(
            MemberProduct(
                buyers = listOf(member), productId = productId, purchaseTime = LocalDateTime.now()
            )
        )
        return OrderSheetResponse(memberId = memberId, productId = productId)
    }

    fun fetchAllPurchasedBy(memberId: Long): List<ProductResponse> {
        // 1. 상품 조회 (구조상, member 로 조회해야 한다. memberProduct 부터 oneToMany 때문에 갈 수가 없음)
//        memberRepository.findAllById() //TODO member 엔티티에 memberProduct 갈 수 있는 필드 하나 생성해야 할듯

        // 2. ProductResponse 로 변환

        return listOf()
    }

}