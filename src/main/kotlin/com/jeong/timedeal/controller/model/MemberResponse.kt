package com.jeong.timedeal.controller.model

data class MemberResponse(
    val id: Long,
    val account: String,
    var name: String? = null,
    var email: String? = null,
    var phone: String? = null
)