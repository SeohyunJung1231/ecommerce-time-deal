package com.jeong.timedeal.repo

import com.jeong.timedeal.entity.MemberProduct
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberProductRepository : JpaRepository<MemberProduct, Long> {
    fun findAllByProductId(productId: Long) : List<MemberProduct>?
}