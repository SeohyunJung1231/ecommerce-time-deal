package com.jeong.timedeal.controller.model

import java.time.LocalDateTime

data class ProductRequest(
    val memberId: Long, // TODO ADMIN 인지 확인 로직 필요
    val name: String? = null,
    val price: Long,
    val stock: Long,
    val saleStartAt: LocalDateTime,
    val saleEndAt: LocalDateTime
)