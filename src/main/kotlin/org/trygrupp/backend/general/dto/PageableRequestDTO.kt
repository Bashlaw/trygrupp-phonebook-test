package org.trygrupp.backend.general.dto

import lombok.Data
import javax.validation.constraints.NotNull

@Data
open class PageableRequestDTO {

    var size: @NotNull(message = "Size must be provided, maximum is 100") Int = 0

    var page: @NotNull(message = "Page must be provided, minimum is 0") Int = 0

}