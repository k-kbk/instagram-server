package com.group.instagramserver.domain.user.api

import com.group.instagramserver.common.ApiResponse
import com.group.instagramserver.domain.user.application.UserService
import com.group.instagramserver.domain.user.dto.SignUpRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService,
) {

    @PostMapping("/sign-up")
    fun signUp(@RequestBody request: SignUpRequest): ApiResponse<String> {
        return ApiResponse.success(userService.signUp(request))
    }
}
