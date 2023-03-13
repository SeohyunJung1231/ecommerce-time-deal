package com.jeong.timedeal.controller.model

import com.jeong.timedeal.entity.SaleTime

data class ProductUpdateRequest (
    val saleTime: SaleTime?,
    val name: String?,
    val price: Long?,
    val stock: Long?
)