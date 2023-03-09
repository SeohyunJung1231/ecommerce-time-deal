package com.jeong.timedeal.controller.model

import com.jeong.timedeal.entity.Member

data class MemberRequest( //TODO validation 추가해야 함
    val name: String? = null, // TODO 이건 null 로 받을지, 아니면 "" 로 기본값을 줄건지?
    val role: Member.Role,
    val account: String,
    val password: String,
    val email: String? = null,
    val phone: String? = null
)