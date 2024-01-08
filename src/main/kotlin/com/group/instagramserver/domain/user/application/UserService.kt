package com.group.instagramserver.domain.user.application

import com.group.instagramserver.domain.user.dto.SignUpRequest
import com.group.instagramserver.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    @Transactional
    fun signUp(request: SignUpRequest): String {
        val user = request.run {
            checkDuplicateEmail(email)
            checkDuplicateUserName(userName)
            this.toEntity()
        }
        userRepository.save(user)

        return "ok"
    }

    @Transactional(readOnly = true)
    fun checkDuplicateUserName(userName: String) {
        require(!userRepository.existsByUserName(userName))
    }

    @Transactional(readOnly = true)
    fun checkDuplicateEmail(email: String) {
        require(!userRepository.existsByEmail(email))
    }
}
