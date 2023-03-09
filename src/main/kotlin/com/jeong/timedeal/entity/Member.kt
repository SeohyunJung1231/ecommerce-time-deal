package com.jeong.timedeal.entity

import jakarta.persistence.*

@Entity
@Table(name = "member")
data class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Enumerated(EnumType.ORDINAL) //TODO STRING, ORDINAL 중 무엇이 더 좋은 코드일지?
    @Column(name = "role_id")//TODO fk 는 insert 시 무조건 매핑을 걸어야 하는지? cascade 란 뭔지?
    val role: Role,

    val account: String,
    val password: String,
    var name: String? = null,
    var email: String? = null,
    var phone: String? = null

) {
    enum class Role {
        ADMIN, USER
    }
}