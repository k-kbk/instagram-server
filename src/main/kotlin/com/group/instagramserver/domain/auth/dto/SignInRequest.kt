package com.group.instagramserver.domain.auth.dto

import jakarta.validation.constraints.NotBlank

data class SignInRequest(
    @field:NotBlank
    val username: String,

    @field:NotBlank
    val password: String,
) {

    companion object {
        fun fixture(
            username: String = "username",
            password: String = "password",
        ): SignInRequest {
            return SignInRequest(username, password)
        }
    }
}
