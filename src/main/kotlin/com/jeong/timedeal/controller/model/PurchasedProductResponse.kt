package com.jeong.timedeal.controller.model

import java.time.LocalDateTime

data class PurchasedProductResponse(
    val id: Long,
    val name: String?,
    val price: Long,
    val purchaseTime: LocalDateTime = LocalDateTime.now()
)