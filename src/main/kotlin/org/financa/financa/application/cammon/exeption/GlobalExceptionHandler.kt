package org.financa.financa.application.cammon.exeption

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.ResponseStatusException

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(ex: ResponseStatusException): ResponseEntity<Any> {
        val body = mapOf(
            "status" to ex.statusCode.value(),
            "error" to ex.statusCode.toString(),
            "message" to (ex.reason ?: "Erro inesperado")
        )
        return ResponseEntity.status(ex.statusCode).body(body)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<Any> {
        val body = mapOf(
            "status" to HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "error" to HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
            "message" to "Erro interno no servidor"
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body)
    }
}
