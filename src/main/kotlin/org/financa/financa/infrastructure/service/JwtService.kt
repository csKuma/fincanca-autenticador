package org.financa.financa.infrastructure.service


import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import java.util.*
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys


@Service
class JwtService {

    var secret=System.getenv("SECRET_JWT")
    private val secretKey = Keys.hmacShaKeyFor(
        secret.toByteArray()
    )

    fun generateToken(email: String): String {
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 3600000)) // expira em 1h
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }
}