package com.jeong.timedeal.entity

import jakarta.persistence.*

@Entity
data class MemberProduct(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @OneToMany
    @JoinColumn(name = "buyer_id")
    val buyers: List<Member> = listOf(),
    val productId: Long
)