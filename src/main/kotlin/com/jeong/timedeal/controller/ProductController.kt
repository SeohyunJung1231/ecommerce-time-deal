package com.jeong.timedeal.controller

import com.jeong.timedeal.controller.model.MemberResponse
import com.jeong.timedeal.controller.model.ProductRequest
import com.jeong.timedeal.service.ProductService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/products")
class ProductController(
    private val productService: ProductService
) {
    @PostMapping
    @Operation(summary = "상품 등록 요청", description = "해당 API 호출시, 상품이 등록된다")
    fun upload(@RequestBody productRequest: ProductRequest): Long {
        return productService.create(productRequest)
    }

//    @GetMapping("/{id}/members")
//    fun getBuyers(@PathVariable id: Long): List<MemberResponse> {
//        return productService.findMembers(id)
//    }
}