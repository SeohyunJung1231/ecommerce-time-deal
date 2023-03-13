package com.jeong.timedeal.controller.model

import com.jeong.timedeal.entity.SaleInfo

data class ProductResponse (
    val id: Long,
    val name: String?,
    val price: Long,
    val stock: Long,
    val saleInfo: SaleInfo
)