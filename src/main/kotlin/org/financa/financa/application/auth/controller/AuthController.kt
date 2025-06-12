package org.financa.financa.application.auth.controller

import org.financa.financa.application.auth.service.GoogleTokenVerifier
import org.financa.financa.application.auth.service.JwtService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val googleTokenVerifier: GoogleTokenVerifier,
    private val jwtService: JwtService
) {
    @PostMapping("/login")
    fun login(@RequestBody request: GoogleLoginRequest): ResponseEntity<Any> {
        println("🔐 ID Token recebido: '${request.idToken}'")

        val payload = googleTokenVerifier.verify(request.idToken)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido")

        val email = payload.email
        val jwt = jwtService.generateToken(email)

        return ResponseEntity.ok(
            mapOf(
                "token" to jwt,
                "email" to email,
                "expiresIn" to 3600 // opcional: tempo de expiração em segundos
            )
        )
    }
}