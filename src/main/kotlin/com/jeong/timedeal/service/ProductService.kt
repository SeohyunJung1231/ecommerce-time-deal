package com.jeong.timedeal.service

import com.jeong.timedeal.controller.model.MemberResponse
import com.jeong.timedeal.controller.model.ProductRequest
import com.jeong.timedeal.entity.Member
import com.jeong.timedeal.entity.Product
import com.jeong.timedeal.entity.SaleTime
import com.jeong.timedeal.repo.MemberProductRepository
import com.jeong.timedeal.repo.MemberRepository
import com.jeong.timedeal.repo.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val memberProductRepository: MemberProductRepository,
    private val memberRepository: MemberRepository
) {
    fun create(productRequest: ProductRequest): Long {
        // TODO 권한쪽 구현되면, ADMIN 회원이 아닌 경우 에러처리 한다
//        if (memberRepository.findById(productRequest.memberId).get().roleId != Member.Type.ADMIN) throw AccessDeniedException()

        val product = Product(
            sellerId = productRequest.memberId,
            name = productRequest.name,
            price = productRequest.price,
            stock = productRequest.stock,
            saleTime = SaleTime( //TODO product 어떻게 넣어야 할지 고민
                saleStartAt = productRequest.saleStartAt, saleEndAt = productRequest.saleEndAt
            )
        )
        return productRepository.save(product).id
    }

    fun findMembers(productId: Long): List<MemberResponse> {
        //TODO memberProductRepository 에서 find 하는게 맞나? 엔티티의 양방향/단방향이 맞는가? 매핑부터 방향 헷갈린다
        val members = memberProductRepository.findByProductId(productId)?.buyers ?: listOf()

        return members.map { member ->
            MemberResponse(
                id = member.id, account = member.account, name = member.name, email = member.email, phone = member.phone
            )
        }

    }
}