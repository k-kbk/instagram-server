package com.group.instagramserver.domain.auth.api

import com.group.instagramserver.common.api.ApiResponse
import com.group.instagramserver.domain.auth.application.AuthService
import com.group.instagramserver.domain.auth.dto.SignInRequest
import com.group.instagramserver.domain.auth.dto.SignInResponse
import com.group.instagramserver.domain.auth.dto.SignUpRequest
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService,
) {

    @PostMapping("/sign-up")
    fun signUp(@RequestBody @Valid request: SignUpRequest): ApiResponse<String> {
        return ApiResponse.success(authService.signUp(request))
    }

    @PostMapping("/sign-in")
    fun signIn(@RequestBody @Valid request: SignInRequest): ApiResponse<SignInResponse> {
        return ApiResponse.success(SignInResponse.from(authService.signIn(request)))
    }
}
