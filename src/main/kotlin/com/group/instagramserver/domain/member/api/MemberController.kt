package com.group.instagramserver.domain.member.api

import com.group.instagramserver.domain.member.application.MemberService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
class MemberController(
    private val memberService: MemberService,
) {

}
