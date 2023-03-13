package com.jeong.timedeal.controller

import com.jeong.timedeal.controller.model.*
import com.jeong.timedeal.repo.MemberRepository
import com.jeong.timedeal.service.MemberService
import com.jeong.timedeal.service.PurchaseService
import io.swagger.v3.oas.annotations.Operation
import jakarta.transaction.Transactional
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/members")
class MemberController(
    private val memberService: MemberService,
    private val memberRepository: MemberRepository,
    private val purchaseService: PurchaseService
) {
    @PostMapping
    @Operation(summary = "회원 가입 요청", description = "해당 API 호출시, 회원 가입됩니다")
    fun register(@RequestBody member: MemberRequest): Long {
        return memberService.create(member)
    }

    @PatchMapping("/{id}")
    @Operation(summary = "회원 수정 요청", description = "해당 API 호출시, 회원 정보가 수정됩니다")
    fun modify(@PathVariable id: Long, @RequestBody member: MemberUpdateRequest): Long {
        return memberService.update(id, member)
    }

    @DeleteMapping("/{id}")
    @Transactional // TODO modifying?
    @Operation(summary = "회원 탈퇴 요청", description = "해당 API 호출시, 회원 탈퇴됩니다")
    fun resign(@PathVariable id: Long): Long {
        memberRepository.deleteById(id)
        return id
    }

    @GetMapping("/{id}")
    @Operation(summary = "회원 조회", description = "해당 API 호출시, 회원 상세정보가 조회됩니다")
    fun get(@PathVariable id: Long): MemberResponse {
        return memberService.get(id)
    }

    @PostMapping("/{memberId}/products/{productId}")
    @Operation(summary = "상품 구매 요청", description = "해당 API 호출시, 상품을 구매합니다")
    fun purchase(@PathVariable memberId: Long, @PathVariable productId: Long): OrderSheetResponse {
        return purchaseService.purchase(memberId, productId)
    }

    @GetMapping("/{memberId}/products")
    @Operation(summary = "구매 상품 목록 요청", description = "해당 API 호출시, 구매한 상품 목록을 가져옵니다")
    fun getAllPurchasedProduct(@PathVariable memberId: Long): List<PurchasedProductResponse> {
        return purchaseService.fetchAllPurchasedBy(memberId)
    }
}