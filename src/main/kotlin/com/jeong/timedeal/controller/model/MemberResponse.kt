package com.jeong.timedeal.controller.model

data class MemberResponse( //TODO 이런 경우도 다 DTO 를 만들어 줘야 하는게 맞나?
    val id: Long,
    val account: String,
    var name: String? = null,
    var email: String? = null,
    var phone: String? = null
)