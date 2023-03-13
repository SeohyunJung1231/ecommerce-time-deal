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
    @JoinColumn(name = "sale_info_id")
    var saleInfo: SaleInfo,

    var name: String? = null,
    var price: Long,
    var stock: Long

)