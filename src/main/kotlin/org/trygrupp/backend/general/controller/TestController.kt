package org.trygrupp.backend.general.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/hello")
@CrossOrigin(origins = ["*"])
class TestController {

    private val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping()
    fun hello(): String {
        val message = "service is running...."
        log.info(message)
        return message
    }

}