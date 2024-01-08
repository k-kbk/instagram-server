package com.group.instagramserver.domain.member.application

import com.group.instagramserver.domain.member.dto.SignUpRequest
import com.group.instagramserver.domain.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {

    @Transactional
    fun signUp(request: SignUpRequest): String {
        val member = request.run {
            checkDuplicateEmail(email)
            checkDuplicateUsername(username)
            this.toEntity()
        }
        memberRepository.save(member)

        return "ok"
    }

    @Transactional(readOnly = true)
    fun checkDuplicateEmail(email: String) {
        require(!memberRepository.existsByEmail(email)) {
            "duplicate email"
        }
    }

    @Transactional(readOnly = true)
    fun checkDuplicateUsername(userName: String) {
        require(!memberRepository.existsByUsername(userName)) {
            "duplicate username"
        }
    }
}
