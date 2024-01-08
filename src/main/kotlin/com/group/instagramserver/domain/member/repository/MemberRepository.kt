package com.group.instagramserver.domain.member.repository

import com.group.instagramserver.domain.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {

    fun existsByEmail(email: String): Boolean

    fun existsByUsername(username: String): Boolean
}
