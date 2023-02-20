package org.trygrupp.backend.general.dto

import lombok.Data
import javax.validation.constraints.NotNull

@Data
open class PageableRequestDTO {

    val size: @NotNull(message = "Size must be provided, maximum is 100") Int = 0

    val page: @NotNull(message = "Page must be provided, minimum is 0") Int = 0

}