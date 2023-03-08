package com.jeong.timedeal.entity

import jakarta.persistence.*

@Entity
@Table(name = "product")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    val sellerId: Long,
    val name: String? = null,
    val price: Long,
    val stock: Long,

    @OneToOne(mappedBy = "product")
    val saleTime: SaleTime

)