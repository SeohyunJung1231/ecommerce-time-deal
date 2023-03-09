package com.jeong.timedeal.entity

import jakarta.persistence.*

@Entity
@Table(name = "product")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "seller_id")
    val sellerId: Long,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "sale_time_id")
    val saleTime: SaleTime,

    val name: String? = null,
    val price: Long,
    val stock: Long

)