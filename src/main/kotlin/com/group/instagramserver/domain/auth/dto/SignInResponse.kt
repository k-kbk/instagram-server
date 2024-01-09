package com.group.instagramserver.domain.auth.dto

data class SignInResponse(
    val token: String,
) {

    companion object {
        fun from(token: String): SignInResponse {
            return SignInResponse(token)
        }
    }
}
