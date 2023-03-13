package com.jeong.timedeal.service

import com.jeong.timedeal.controller.model.MemberResponse
import com.jeong.timedeal.controller.model.ProductRequest
import com.jeong.timedeal.controller.model.ProductResponse
import com.jeong.timedeal.controller.model.ProductUpdateRequest
import com.jeong.timedeal.entity.Product
import com.jeong.timedeal.entity.SaleInfo
import com.jeong.timedeal.repo.MemberProductRepository
import com.jeong.timedeal.repo.MemberRepository
import com.jeong.timedeal.repo.ProductRepository
import com.jeong.timedeal.repo.SaleInfoRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val memberProductRepository: MemberProductRepository,
    private val memberRepository: MemberRepository,
    private val saleInfoRepository: SaleInfoRepository
) {
    fun create(productRequest: ProductRequest): Long {
        // TODO 권한쪽 구현되면, ADMIN 회원이 아닌 경우 에러처리 한다
//        if (memberRepository.findById(productRequest.memberId).get().roleId != Member.Type.ADMIN) throw AccessDeniedException()

        val product = Product(
            sellerId = productRequest.memberId,
            name = productRequest.name,
            price = productRequest.price,
            stock = productRequest.stock,
            saleInfo = SaleInfo(
                saleStartAt = productRequest.saleInfo.saleStartAt,
                saleEndAt = productRequest.saleInfo.saleEndAt,
                discount = productRequest.saleInfo.discount,
                price = productRequest.saleInfo.price
            )
        )
        return productRepository.save(product).id
    }

    fun update(id: Long, productUpdateRequest: ProductUpdateRequest): Long {
        require(exist(id)) { "product with id: $id does not exist" }
        val product: Product = productRepository.findById(id).get()

        productRepository.save(product.apply {
            name = productUpdateRequest.name ?: name
            price = productUpdateRequest.price ?: price
            stock = productUpdateRequest.stock ?: stock
        })
        return id
    }

    fun get(productId: Long): ProductResponse {
        require(exist(productId)) { "product with id: $productId does not exist" }
        return productRepository.findById(productId).get().let { product ->
            ProductResponse(
                id = product.id,
                name = product.name,
                price = product.price,
                stock = product.stock,
                saleInfo = product.saleInfo
            )
        }
    }

    fun getAll(): List<ProductResponse> {
        //TODO JPA 페이징기능 추가
        val products = productRepository.findAll() ?: listOf()
        return products.map {
            ProductResponse(
                id = it.id, name = it.name, price = it.price, stock = it.stock, saleInfo = it.saleInfo
            )
        }
    }

    fun findMembers(productId: Long): List<MemberResponse> {
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