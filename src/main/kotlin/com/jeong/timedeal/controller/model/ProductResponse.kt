package com.jeong.timedeal.controller.model

import java.time.LocalDateTime

data class ProductResponse(
    val id: Long,
    val price: Long,
    val purchaseTime: LocalDateTime = LocalDateTime.now()
)