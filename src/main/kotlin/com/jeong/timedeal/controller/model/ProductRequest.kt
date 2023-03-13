package com.jeong.timedeal.controller.model

import com.jeong.timedeal.entity.SaleInfo

data class ProductRequest(
    val memberId: Long, // TODO ADMIN 인지 확인 로직 필요
    val name: String? = null,
    val price: Long,
    val stock: Long,
    val saleInfo: SaleInfo
)