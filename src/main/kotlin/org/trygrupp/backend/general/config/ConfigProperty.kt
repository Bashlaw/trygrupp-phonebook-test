package org.trygrupp.backend.general.config

import lombok.Data
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Data
@Configuration
class ConfigProperty {

    @Value("\${max-pull-size}")
    private val paystackSecret: String? = null

}