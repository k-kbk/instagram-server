package com.group.instagramserver.domain.member.entity

import com.group.instagramserver.common.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "member")
class Member(
    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false, unique = true)
    var username: String,

    @Column(nullable = false)
    var password: String,
) : BaseEntity() {

    companion object {
        const val MIN_NAME_LENGTH = 2
        const val MAX_NAME_LENGTH = 32
        const val MIN_USERNAME_LENGTH = 4
        const val MAX_USERNAME_LENGTH = 32
        const val MIN_PASSWORD_LENGTH = 8
        const val MAX_PASSWORD_LENGTH = 32

        fun fixture(
            email: String = "email@email.com",
            name: String = "name",
            username: String = "username",
            password: String = "password",
        ): Member {
            return Member(email, name, username, password)
        }
    }
}
