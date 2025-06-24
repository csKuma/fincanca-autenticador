package org.financa.financa.infrastructure.auth.service


import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import java.util.*
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.financa.financa.domain.auth.service.TokenService


@Service
class JwtService: TokenService {

    var secret=System.getenv("SECRET_JWT")
    private val secretKey = Keys.hmacShaKeyFor(
        secret.toByteArray()
    )

    override fun generateToken(email: String): String {
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 3600000)) // expira em 1h
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }
}