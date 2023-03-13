package com.jeong.timedeal.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "sale_info")
data class SaleInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "sale_start_at")
    val saleStartAt: LocalDateTime,
    @Column(name = "sale_end_at")
    val saleEndAt: LocalDateTime,
    val discount: Byte,
    val price: Long
)