package com.group.instagramserver.common.vo

@JvmInline
value class Email(
    val value: String,
) {
    init {
        validEmail(value)
    }

    private fun validEmail(email: String) {
        require(email.isNotBlank() && email.contains("@"))
    }

    companion object {
        fun fixture(email: String = "email@email.com"): Email {
            return Email(email)
        }
    }
}