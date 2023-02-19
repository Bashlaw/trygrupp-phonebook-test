package org.trygrupp.backend.utils

import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.Data
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Data
class BaseEntityDTO {

    private var createdDate: String? = null
    private var updatedDate: String? = null

    @JsonIgnore
    private var createdAt: LocalDateTime? = null

    @JsonIgnore
    private var updatedAt: LocalDateTime? = null

    fun setCreatedAt(createdAt: LocalDateTime?) {
        this.createdAt = createdAt
        if (this.createdAt != null) {
            createdDate = this.createdAt!!.format(customFormat)
        }
    }

    fun setUpdatedAt(updatedAt: LocalDateTime?) {
        this.updatedAt = updatedAt
        if (this.updatedAt != null) {
            updatedDate = this.updatedAt!!.format(customFormat)
        }
    }

    companion object {
        @JsonIgnore
        private val customFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    }

}