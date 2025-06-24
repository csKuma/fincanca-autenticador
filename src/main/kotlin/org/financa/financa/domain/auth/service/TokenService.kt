package org.financa.financa.domain.auth.service

interface TokenService {
    fun generateToken(email: String): String
}