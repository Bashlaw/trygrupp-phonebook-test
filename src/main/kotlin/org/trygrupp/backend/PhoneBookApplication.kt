package org.trygrupp.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class PhoneBookApplication

fun main(args: Array<String>) {
    runApplication<PhoneBookApplication>(*args)
}

