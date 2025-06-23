package org.financa.financa.application.auth.usecase

import org.financa.financa.application.cammon.exeption.UnauthorizedException
import org.financa.financa.domain.auth.service.TokenService
import org.financa.financa.infrastructure.auth.service.GoogleTokenVerifier
import org.financa.financa.interfaces.auth.dto.AuthResponse
import org.springframework.stereotype.Service

@Service
class AuthenticateWithGoogleUseCase(
    private val googleTokenVerifier: GoogleTokenVerifier,
    private val tokenService: TokenService
) {
    fun execute(idToken: String): AuthResponse {
        val payload = googleTokenVerifier.verify(idToken)
            ?: throw UnauthorizedException("Token inválido")

        val token = tokenService.generateToken(payload.email)
        return AuthResponse(token, payload.email, 3600)
    }
}
