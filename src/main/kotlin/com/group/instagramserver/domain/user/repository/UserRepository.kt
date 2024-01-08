package com.group.instagramserver.domain.user.repository

import com.group.instagramserver.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun existsByEmail(email: String): Boolean

    fun existsByUsername(username: String): Boolean
}
