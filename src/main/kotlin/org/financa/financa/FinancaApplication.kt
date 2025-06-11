package org.financa.financa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FinancaApplication

fun main(args: Array<String>) {
    runApplication<FinancaApplication>(*args)
}
