package com.jeong.timedeal.domain

import jakarta.persistence.*

@Entity
data class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Enumerated(EnumType.ORDINAL) //TODO 어떻게 해야 더 좋은 코드일지?
    val roleId: Type,

    val account: String,
    val name: String?,
    val email: String,
    val phone: String?

) {
    enum class Type {
        ADMIN, USER
    }
}