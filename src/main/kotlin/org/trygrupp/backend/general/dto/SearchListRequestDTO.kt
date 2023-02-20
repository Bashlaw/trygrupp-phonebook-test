package org.trygrupp.backend.general.dto

import lombok.Data
import lombok.EqualsAndHashCode

@Data
@EqualsAndHashCode(callSuper = true)
open class SearchListRequestDTO : PageableRequestDTO() {

    var fromDate: String? = null

    var toDate: String? = null

}