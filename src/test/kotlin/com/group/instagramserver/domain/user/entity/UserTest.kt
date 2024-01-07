package com.group.instagramserver.domain.user.entity

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class UserTest : BehaviorSpec({
    Given("사용자 생성 시에") {
        When("이름이 빈 값이면") {
            val result = shouldThrowAny {
                User.fixture(name = "")
            }

            then("예외를 던진다") {
                result::class shouldBe IllegalArgumentException::class
            }
        }

        When("이름이 최소 길이보다 짧으면") {
            val name = "T".repeat(User.MIN_NAME_LENGTH - 1)
            val result = shouldThrowAny {
                User.fixture(name = name)
            }

            then("예외를 던진다") {
                result::class shouldBe IllegalArgumentException::class
            }
        }

        When("이름이 최대 길이보다 길면") {
            val name = "T".repeat(User.MAX_NAME_LENGTH + 1)
            val result = shouldThrowAny {
                User.fixture(name = name)
            }

            then("예외를 던진다") {
                result::class shouldBe IllegalArgumentException::class
            }
        }

        When("사용자명이 빈 값이면") {
            val result = shouldThrowAny {
                User.fixture(userName = "")
            }

            then("예외를 던진다") {
                result::class shouldBe IllegalArgumentException::class
            }
        }

        When("사용자명이 최소 길이보다 짧으면") {
            val userName = "T".repeat(User.MIN_USERNAME_LENGTH - 1)
            val result = shouldThrowAny {
                User.fixture(userName = userName)
            }

            then("예외를 던진다") {
                result::class shouldBe IllegalArgumentException::class
            }
        }

        When("사용자명이 최대 길이보다 길면") {
            val userName = "T".repeat(User.MAX_USERNAME_LENGTH + 1)
            val result = shouldThrowAny {
                User.fixture(userName = userName)
            }

            then("예외를 던진다") {
                result::class shouldBe IllegalArgumentException::class
            }
        }

        When("비밀번호가 빈 값이면") {
            val result = shouldThrowAny {
                User.fixture(password = "")
            }

            then("예외를 던진다") {
                result::class shouldBe IllegalArgumentException::class
            }
        }

        When("비밀번호가 최소 길이보다 짧으면") {
            val password = "T".repeat(User.MIN_PASSWORD_LENGTH - 1)
            val result = shouldThrowAny {
                User.fixture(password = password)
            }

            then("예외를 던진다") {
                result::class shouldBe IllegalArgumentException::class
            }
        }

        When("비밀번호가 최대 길이보다 길면") {
            val password = "T".repeat(User.MAX_PASSWORD_LENGTH + 1)
            val result = shouldThrowAny {
                User.fixture(password = password)
            }

            then("예외를 던진다") {
                result::class shouldBe IllegalArgumentException::class
            }
        }

        When("모든 값이 정상적으로 입력되면") {
            val result = User.fixture()

            then("객체를 생성한다") {
                result::class shouldBe User::class
            }
        }
    }
})
