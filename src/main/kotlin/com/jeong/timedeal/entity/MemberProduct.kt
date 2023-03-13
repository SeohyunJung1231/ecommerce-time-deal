package com.jeong.timedeal.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "member_product")
data class MemberProduct(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    val buyer: Member,
    @Column(name = "product_id")
    val productId: Long,

    @Column(name = "purchase_time")
    val purchaseTime: LocalDateTime
)