package com.group.instagramserver.domain.user.application

import com.group.instagramserver.domain.user.dto.SignUpRequest
import com.group.instagramserver.domain.user.entity.User
import com.group.instagramserver.domain.user.repository.UserRepository
import io.kotest.assertions.throwables.shouldThrowAny
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
        val userName = "test"
        userRepository.save(
            User.fixture(
                email = email,
                userName = userName
            )
        )

        When("이미 사용 중인 이메일을 입력하면") {
            val request = SignUpRequest.fixture(email = email)
            val exception = shouldThrowAny {
                userService.signUp(request)
            }

            Then("예외를 던진다") {
                exception::class shouldBe IllegalArgumentException::class
            }
        }

        When("이미 사용 중인 사용자명을 입력하면") {
            val request = SignUpRequest.fixture(userName = userName)
            val exception = shouldThrowAny {
                userService.signUp(request)
            }

            Then("예외를 던진다") {
                exception::class shouldBe IllegalArgumentException::class
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
