package com.jeong.timedeal.entity

import jakarta.persistence.*

@Entity
@Table(name = "member_product")
data class MemberProduct(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @OneToMany
    @JoinColumn(name = "buyer_id")
    val buyers: List<Member> = listOf(),
    @Column(name = "product_id")
    val productId: Long
)