package com.group.instagramserver.domain.user.dto

import com.group.instagramserver.domain.user.entity.User
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

data class SignUpRequest(
    @NotBlank
    @Email
    val email: String,

    @NotBlank
    @Length(min = User.MIN_NAME_LENGTH, max = User.MAX_NAME_LENGTH)
    val name: String,

    @NotBlank
    @Length(min = User.MIN_USERNAME_LENGTH, max = User.MAX_USERNAME_LENGTH)
    val userName: String,

    @NotBlank
    @Length(min = User.MIN_PASSWORD_LENGTH, max = User.MAX_PASSWORD_LENGTH)
    val password: String,
) {
    
    fun toEntity(): User {
        return User(
            email,
            name,
            userName,
            password,
        )
    }

    companion object {
        fun fixture(
            email: String = "email@email.com",
            name: String = "name",
            userName: String = "userName",
            password: String = "password",
        ): SignUpRequest {
            return SignUpRequest(email, name, userName, password)
        }
    }
}
