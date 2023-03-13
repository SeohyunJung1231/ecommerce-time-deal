package com.jeong.timedeal.controller

import com.jeong.timedeal.controller.model.*
import com.jeong.timedeal.repo.ProductRepository
import com.jeong.timedeal.service.ProductService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/products")
class ProductController( //TODO request body validation 추가
    private val productService: ProductService,
    private val productRepository: ProductRepository
) {
    @PostMapping
    @Operation(summary = "상품 등록 요청", description = "해당 API 호출시, 상품이 등록된다")
    fun upload(@RequestBody productRequest: ProductRequest): Long {
        return productService.create(productRequest)
    }
    @PatchMapping("/{id}")
    @Operation(summary = "상품 수정 요청", description = "해당 API 호출시, 상품이 수정된다")
    fun modify(@PathVariable id: Long, @RequestBody product: ProductUpdateRequest): Long {
        return productService.update(id, product)
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "상품 제거 요청", description = "해당 API 호출시, 상품이 제거된다")
    fun delete(@PathVariable id: Long): Long {
        productRepository.deleteById(id)
        return id
    }

    @GetMapping("/{id}")
    @Operation(summary = "상품 조회 요청", description = "해당 API 호출시, 상품의 상세 정보가 조회된다")
    fun get(@PathVariable id : Long) : ProductResponse {
        return productService.get(id)
    }

    @GetMapping
    @Operation(summary = "상품 목록 요청", description = "해당 API 호출시, 상품이 목록이 조회된다")
    fun getAll(): List<ProductResponse> {
        return productService.getAll()
    }


    @GetMapping("/{id}/members")
    @Operation(summary = "상품 구매자 목록", description = "해당 API 호출시, 상품을 구매한 구매자의 목록이 반환된다")
    fun getBuyers(@PathVariable id: Long): List<MemberResponse> {
        return productService.findMembers(id)
    }
}