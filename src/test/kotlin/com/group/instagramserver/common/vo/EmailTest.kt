package com.group.instagramserver.common.vo

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class EmailTest : BehaviorSpec({
    Given("이메일이") {
        When("빈 값이면") {
            val result = shouldThrowAny {
                Email("")
            }

            Then("예외를 던진다") {
                result::class shouldBe IllegalArgumentException::class
            }
        }

        When("형식에 맞지 않으면") {
            val result = shouldThrowAny {
                Email("email")
            }

            Then("예외를 던진다") {
                result::class shouldBe IllegalArgumentException::class
            }
        }

        When("정상적으로 입력되면") {
            val result = Email.fixture()

            Then("객체를 생성한다") {
                result::class shouldBe Email::class
            }
        }
    }
})
