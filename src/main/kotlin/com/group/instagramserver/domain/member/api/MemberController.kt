package com.group.instagramserver.domain.member.api

import com.group.instagramserver.common.ApiResponse
import com.group.instagramserver.domain.member.application.MemberService
import com.group.instagramserver.domain.member.dto.SignUpRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
class MemberController(
    private val memberService: MemberService,
) {

    @PostMapping("/sign-up")
    fun signUp(@RequestBody request: SignUpRequest): ApiResponse<String> {
        return ApiResponse.success(memberService.signUp(request))
    }
}
