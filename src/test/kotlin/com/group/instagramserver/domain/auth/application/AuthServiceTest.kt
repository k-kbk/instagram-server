package com.group.instagramserver.domain.auth.application

import com.group.instagramserver.domain.auth.dto.SignInRequest
import com.group.instagramserver.domain.auth.dto.SignUpRequest
import com.group.instagramserver.domain.member.repository.MemberRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootTest
class AuthServiceTest @Autowired constructor(
    private val authService: AuthService,
    private val memberRepository: MemberRepository,
    private val encoder: PasswordEncoder,
) : BehaviorSpec({

    Given("인증") {
        val email = "test@test.com"
        val username = "test"
        val password = "test-test"

        memberRepository.save(
            SignUpRequest.fixture(
                email = email,
                username = username,
                password = password
            ).toEntity(encoder)
        )

        And("- 회원가입") {
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

        And("- 로그인") {
            When("존재하지 않는 사용자명을 입력하면") {
                val request = SignInRequest.fixture(username = username.reversed())
                val result = shouldThrow<IllegalArgumentException> {
                    authService.signIn(request)
                }

                Then("예외를 던진다") {
                    result.message shouldBe "wrong username or password"
                }
            }

            When("사용자명과 일치하지 않는 비밀번호를 입력하면") {
                val request = SignInRequest.fixture(username = username)
                val result = shouldThrow<IllegalArgumentException> {
                    authService.signIn(request)
                }

                Then("예외를 던진다") {
                    result.message shouldBe "wrong username or password"
                }
            }

            When("모든 값을 올바르게 입력하면") {
                val request = SignInRequest.fixture(
                    username = username,
                    password = password
                )
                val result = authService.signIn(request)

                Then("로그인에 성공한다") {
                    println(result)
                    result::class shouldBe String::class
                }
            }
        }
    }
})
