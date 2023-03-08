package com.jeong.timedeal.service

import com.jeong.timedeal.controller.model.MemberResponse
import com.jeong.timedeal.controller.model.ProductRequest
import com.jeong.timedeal.entity.Member
import com.jeong.timedeal.entity.Product
import com.jeong.timedeal.entity.SaleTime
import com.jeong.timedeal.repo.MemberRepository
import com.jeong.timedeal.repo.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val memberRepository: MemberRepository
) {
    fun create(productRequest: ProductRequest): Long {
        // TODO 권한쪽 구현되면, ADMIN 회원이 아닌 경우 에러처리 한다
//        if (memberRepository.findById(productRequest.memberId).get().roleId != Member.Type.ADMIN) throw AccessDeniedException()

        val product = Product(
            memberId = productRequest.memberId,
            name = productRequest.name,
            price = productRequest.price,
            stock = productRequest.stock,
            saleTime = SaleTime( //TODO product 어떻게 넣어야 할지 고민
                saleStartAt = productRequest.saleStartAt,
                saleEndAt = productRequest.saleEndAt
            )
        )
        return productRepository.save(product).id
    }

//    fun findMembers(productId: Long): List<MemberResponse> {
//        productRepository.findAllById
//    }
}