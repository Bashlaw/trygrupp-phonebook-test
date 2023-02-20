package org.trygrupp.backend.general.dto

import lombok.Data

@Data
open class PageableResponseDTO {

    var hasNextRecord = false

    var totalCount = 0

}