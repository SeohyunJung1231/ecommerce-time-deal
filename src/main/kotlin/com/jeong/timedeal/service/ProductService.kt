package com.jeong.timedeal.service

import com.jeong.timedeal.controller.model.MemberResponse
import com.jeong.timedeal.controller.model.ProductRequest
import com.jeong.timedeal.controller.model.ProductResponse
import com.jeong.timedeal.controller.model.ProductUpdateRequest
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

    fun update(id: Long, productUpdateRequest: ProductUpdateRequest): Long {
        val product: Product = productRepository.findById(id).get()
        return productRepository.save(product.apply {
            name = productUpdateRequest.name ?: name
            price = productUpdateRequest.price ?: price
            stock = productUpdateRequest.stock ?: stock
            saleTime = productUpdateRequest.saleTime ?: saleTime //TODO 레코드 추가되는지 확인
        }).id
    }

    fun get(productId: Long): ProductResponse {
        require(exist(productId)) { "product with id: $productId does not exist" }
        return productRepository.findById(productId).get().let { product ->
            ProductResponse(
                id = product.id,
                name = product.name,
                price = product.price,
                stock = product.stock,
                saleTime = product.saleTime
            )
        }
    }

    fun getAll(): List<ProductResponse> {
        //TODO JPA 페이징 추가하기 필요
        val products = productRepository.findAll() ?: listOf()
        return products.map {
            ProductResponse(
                id = it.id, name = it.name, price = it.price, stock = it.stock, saleTime = it.saleTime
            )
        }
    }

    fun findMembers(productId: Long): List<MemberResponse> {
        //TODO memberProductRepository 에서 find 하는게 맞나? 엔티티의 양방향/단방향이 맞는가? 매핑부터 방향 헷갈린다
        val memberProducts = memberProductRepository.findAllByProductId(productId) ?: listOf()

        return memberProducts.map { memberProduct ->
            MemberResponse(
                id = memberProduct.buyer.id,
                account = memberProduct.buyer.account,
                name = memberProduct.buyer.name,
                email = memberProduct.buyer.email,
                phone = memberProduct.buyer.phone
            )
        }
    }

    private fun exist(id: Long) = !productRepository.findById(id).isEmpty
}