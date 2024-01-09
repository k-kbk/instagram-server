package com.group.instagramserver.domain.auth.application

import com.group.instagramserver.domain.auth.dto.SignUpRequest
import com.group.instagramserver.domain.member.repository.MemberRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val memberRepository: MemberRepository,
    private val encoder: PasswordEncoder,
) {

    @Transactional
    fun signUp(request: SignUpRequest): String {
        val member = request.run {
            checkDuplicateEmail(email)
            checkDuplicateUsername(username)
            this.toEntity(encoder)
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
