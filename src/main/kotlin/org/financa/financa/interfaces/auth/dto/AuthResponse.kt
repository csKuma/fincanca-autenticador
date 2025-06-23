package org.financa.financa.interfaces.auth.dto

data class AuthResponse(
    val token: String,
    val email: String,
    val expiresIn: Long
)
