package com.jeong.timedeal.repo

import com.jeong.timedeal.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : JpaRepository<Member, Long>{
    fun findByAccount(account: String): Member?
}