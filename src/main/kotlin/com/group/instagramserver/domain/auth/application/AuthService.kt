package com.group.instagramserver.domain.auth.application

import com.group.instagramserver.domain.auth.dto.SignInRequest
import com.group.instagramserver.domain.auth.dto.SignUpRequest
import com.group.instagramserver.domain.member.repository.MemberRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val memberRepository: MemberRepository,
    private val encoder: PasswordEncoder,
    private val tokenProvider: TokenProvider,
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

    @Transactional
    fun signIn(request: SignInRequest): String {
        val token = memberRepository.findByUsername(request.username)?.run {
            checkPassword(
                rawPassword = request.password,
                encodedPassword = password,
            )
            tokenProvider.createToken(id)
        } ?: throw IllegalArgumentException("wrong username or password")

        return token
    }

    fun checkPassword(rawPassword: String, encodedPassword: String) {
        require(encoder.matches(rawPassword, encodedPassword)) {
            "wrong username or password"
        }
    }
}
