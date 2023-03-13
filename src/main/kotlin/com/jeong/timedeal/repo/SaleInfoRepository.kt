package com.jeong.timedeal.repo

import com.jeong.timedeal.entity.SaleInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SaleInfoRepository: JpaRepository<SaleInfo, Long> {
}