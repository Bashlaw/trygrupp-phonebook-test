package org.trygrupp.backend.general.dto

import lombok.Data
import lombok.EqualsAndHashCode

@Data
@EqualsAndHashCode(callSuper = true)
open class SearchListRequestDTO : PageableRequestDTO() {

    val fromDate: String? = null

    val toDate: String? = null

}