package com.jeong.timedeal.controller.model

data class ProductUpdateRequest (
//    val saleInfo: SaleInfo?, //TODO 추가 개발건
    val name: String?,
    val price: Long?,
    val stock: Long?
)