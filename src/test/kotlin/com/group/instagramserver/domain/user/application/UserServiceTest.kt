package com.group.instagramserver.domain.user.application

import com.group.instagramserver.domain.user.dto.SignUpRequest
import com.group.instagramserver.domain.user.entity.User
import com.group.instagramserver.domain.user.repository.UserRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userService: UserService,
    private val userRepository: UserRepository,
) : BehaviorSpec({

    Given("회원가입") {
        val email = "test@test.com"
        val username = "test"
        userRepository.save(
            User.fixture(
                email = email,
                username = username
            )
        )

        When("이미 사용 중인 이메일을 입력하면") {
            val request = SignUpRequest.fixture(email = email)
            val result = shouldThrow<IllegalArgumentException> {
                userService.signUp(request)
            }

            Then("예외를 던진다") {
                result.message shouldBe "duplicate email"
            }
        }

        When("이미 사용 중인 사용자명을 입력하면") {
            val request = SignUpRequest.fixture(username = username)
            val result = shouldThrow<IllegalArgumentException> {
                userService.signUp(request)
            }

            Then("예외를 던진다") {
                result.message shouldBe "duplicate username"
            }
        }

        When("모든 값을 정상적으로 입력하면") {
            val request = SignUpRequest.fixture()
            val result = userService.signUp(request)

            Then("회원가입에 성공한다") {
                result shouldBe "ok"
            }
        }
    }
})
