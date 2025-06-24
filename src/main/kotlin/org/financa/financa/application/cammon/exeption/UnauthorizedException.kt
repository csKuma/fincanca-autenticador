package org.financa.financa.application.cammon.exeption

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class UnauthorizedException(message: String) : ResponseStatusException(HttpStatus.UNAUTHORIZED, message)
