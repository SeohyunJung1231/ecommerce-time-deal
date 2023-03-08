package com.jeong.timedeal.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "sale_time")
data class SaleTime(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    val product: Product? = null, //TODO 널로 잡는게 맞는지?

    val saleStartAt: LocalDateTime,
    val saleEndAt: LocalDateTime
)