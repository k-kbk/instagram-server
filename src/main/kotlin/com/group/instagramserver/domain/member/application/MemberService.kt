package com.group.instagramserver.domain.member.application

import com.group.instagramserver.domain.member.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {

}
