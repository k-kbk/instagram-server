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

    @Column(nullable = false)
    var introduction: String = "",

    @Column
    var profileImage: String? = null,
) : BaseEntity() {

    companion object {
        const val MIN_NAME_LENGTH = 2
        const val MAX_NAME_LENGTH = 31
        const val MIN_USERNAME_LENGTH = 4
        const val MAX_USERNAME_LENGTH = 31
        const val MIN_PASSWORD_LENGTH = 8
        const val MAX_PASSWORD_LENGTH = 31
        const val MAX_INTRODUCTION_LENGTH = 255

        fun fixture(
            email: String = "email@email.com",
            name: String = "name",
            username: String = "username",
            password: String = "password",
            introduction: String = "introduction",
            profileImage: String? = null,
        ): Member {
            return Member(email, name, username, password, introduction, profileImage)
        }
    }
}
