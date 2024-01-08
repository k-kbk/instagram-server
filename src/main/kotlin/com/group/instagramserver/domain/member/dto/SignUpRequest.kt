package com.group.instagramserver.domain.member.dto

import com.group.instagramserver.domain.member.entity.Member
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

data class SignUpRequest(
    @NotBlank
    @Email
    val email: String,

    @NotBlank
    @Length(min = Member.MIN_NAME_LENGTH, max = Member.MAX_NAME_LENGTH)
    val name: String,

    @NotBlank
    @Length(min = Member.MIN_USERNAME_LENGTH, max = Member.MAX_USERNAME_LENGTH)
    val username: String,

    @NotBlank
    @Length(min = Member.MIN_PASSWORD_LENGTH, max = Member.MAX_PASSWORD_LENGTH)
    val password: String,
) {

    fun toEntity(): Member {
        return Member(
            email,
            name,
            username,
            password,
        )
    }

    companion object {
        fun fixture(
            email: String = "email@email.com",
            name: String = "name",
            username: String = "username",
            password: String = "password",
        ): SignUpRequest {
            return SignUpRequest(email, name, username, password)
        }
    }
}
