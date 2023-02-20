package org.trygrupp.backend.utils

import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.util.*

object GeneralUtil {

    private val log = LoggerFactory.getLogger(this::class.java)

    fun stringIsNullOrEmpty(value: String): Boolean {
        return Objects.isNull(value) || value.isEmpty()
    }

}