package com.group.instagramserver.domain.user.entity

import com.group.instagramserver.common.entity.BaseEntity
import com.group.instagramserver.common.vo.Email
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Table(name = "user")
@Entity
class User(
    @Column
    var email: Email,
) : BaseEntity() {
    constructor(
        email: Email,
        name: String,
        userName: String,
        password: String,
    ) : this(email) {
        this.name = name.apply { validName(this) }
        this.userName = userName.apply { validUserName(this) }
        this.password = password.apply { validPassword(this) }
    }

    @Column
    var name: String = ""

    @Column
    var userName: String = ""

    @Column
    var password: String = ""

    private fun validName(name: String) {
        require(
            name.isNotEmpty() &&
                    name.length in MIN_NAME_LENGTH..MAX_NAME_LENGTH
        )
    }

    private fun validUserName(userName: String) {
        require(
            userName.isNotBlank() &&
                    userName.length in MIN_USERNAME_LENGTH..MAX_USERNAME_LENGTH
        )
    }

    private fun validPassword(password: String) {
        require(
            password.isNotBlank() &&
                    password.length in MIN_PASSWORD_LENGTH..MAX_PASSWORD_LENGTH
        )
    }

    companion object {
        const val MIN_NAME_LENGTH = 2
        const val MAX_NAME_LENGTH = 32
        const val MIN_USERNAME_LENGTH = 4
        const val MAX_USERNAME_LENGTH = 32
        const val MIN_PASSWORD_LENGTH = 8
        const val MAX_PASSWORD_LENGTH = 32

        fun fixture(
            email: Email = Email.fixture(),
            name: String = "name",
            userName: String = "userName",
            password: String = "password",
        ): User {
            return User(email, name, userName, password)
        }
    }
}