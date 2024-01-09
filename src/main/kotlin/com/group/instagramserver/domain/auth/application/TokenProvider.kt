package com.group.instagramserver.domain.auth.application

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenProvider(
    @Value("\${jwt.secret}")
    private val secret: String,
) {

    fun createToken(memberId: Long, expirationHours: Int = 3): String {
        val now = Date()
        val expiration = Date(now.time + expirationHours * 1000 * 60 * 60)
        val secretKey = Keys.hmacShaKeyFor(secret.toByteArray())

        return Jwts.builder()
            .claim("id", memberId)
            .issuedAt(now)
            .expiration(expiration)
            .signWith(secretKey)
            .compact()
    }
}
