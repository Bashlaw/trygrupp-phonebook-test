package org.trygrupp.backend.utils

import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.util.*

object GeneralUtil {

    private val log = LoggerFactory.getLogger(this::class.java)

    fun stringIsNullOrEmpty(value: String): Boolean {
        return Objects.isNull(value) || value.isEmpty()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val reference = "test_20191123132233"
        log.info(LocalDateTime.parse("2022-12-13T00:00:00").toString())
    }

}