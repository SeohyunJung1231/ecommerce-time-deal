package com.jeong.timedeal.domain

import jakarta.persistence.*

@Entity
@Table(name = "member")
data class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Enumerated(EnumType.ORDINAL) //TODO 어떻게 해야 더 좋은 코드일지?
    @Column(name = "role_id")
    val roleId: Type,

    val account: String,
    val password: String,
    var name: String?,
    var email: String,
    var phone: String?

) {
    enum class Type {
        ADMIN, USER
    }
}