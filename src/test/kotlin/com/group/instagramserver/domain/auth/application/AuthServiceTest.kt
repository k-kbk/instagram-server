package com.group.instagramserver.domain.auth.application

import com.group.instagramserver.domain.auth.dto.SignUpRequest
import com.group.instagramserver.domain.member.entity.Member
import com.group.instagramserver.domain.member.repository.MemberRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AuthServiceTest @Autowired constructor(
    private val authService: AuthService,
    private val memberRepository: MemberRepository,
) : BehaviorSpec({

    Given("회원가입") {
        val email = "test@test.com"
        val username = "test"
        memberRepository.save(
            Member.fixture(
                email = email,
                username = username
            )
        )

        When("이미 사용 중인 이메일을 입력하면") {
            val request = SignUpRequest.fixture(email = email)
            val result = shouldThrow<IllegalArgumentException> {
                authService.signUp(request)
            }

            Then("예외를 던진다") {
                result.message shouldBe "duplicate email"
            }
        }

        When("이미 사용 중인 사용자명을 입력하면") {
            val request = SignUpRequest.fixture(username = username)
            val result = shouldThrow<IllegalArgumentException> {
                authService.signUp(request)
            }

            Then("예외를 던진다") {
                result.message shouldBe "duplicate username"
            }
        }

        When("모든 값을 정상적으로 입력하면") {
            val request = SignUpRequest.fixture()
            val result = authService.signUp(request)

            Then("회원가입에 성공한다") {
                result shouldBe "ok"
            }
        }
    }
})
