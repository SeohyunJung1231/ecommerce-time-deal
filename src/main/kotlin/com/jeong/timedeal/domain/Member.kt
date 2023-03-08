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
    var name: String? = null,
    var email: String? = null,
    var phone: String? = null

) {
    enum class Type {
        ADMIN, USER
    }
}