//package org.financa.financa.interfaces.auth
//
//import org.financa.financa.application.auth.usecase.AuthenticateWithGoogleUseCase
//import org.financa.financa.interfaces.auth.dto.AuthResponse
//import org.financa.financa.interfaces.auth.dto.GoogleLoginRequest
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.PostMapping
//import org.springframework.web.bind.annotation.RequestBody
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RestController
//
////@RestController
////@RequestMapping("/auth")
////class AuthController(
////    private val googleTokenVerifier: GoogleTokenVerifier,
////    private val jwtService: JwtService
////) {
////    @PostMapping("/login")
////    fun login(@RequestBody request: GoogleLoginRequest): ResponseEntity<Any> {
////        println("🔐 ID Token recebido: '${request.idToken}'")
////
////        val payload = googleTokenVerifier.verify(request.idToken)
////            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido")
////
////        val email = payload.email
////        val jwt = jwtService.generateToken(email)
////
////        return ResponseEntity.ok(
////            mapOf(
////                "token" to jwt,
////                "email" to email,
////                "expiresIn" to 3600 // opcional: tempo de expiração em segundos
////            )
////        )
////    }
////}
//
//@RestController
//@RequestMapping("/auth")
//class AuthController(
//    private val authenticateWithGoogleUseCase: AuthenticateWithGoogleUseCase
//) {
//
//    @PostMapping("/login")
//    fun login(@RequestBody request: GoogleLoginRequest): ResponseEntity<AuthResponse> {
//        val response = authenticateWithGoogleUseCase.execute(request.idToken)
//        return ResponseEntity.ok(response)
//    }
//
//}
