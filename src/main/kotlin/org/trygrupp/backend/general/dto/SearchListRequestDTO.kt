package org.trygrupp.backend.general.dto

import lombok.Data
import lombok.EqualsAndHashCode

@Data
@EqualsAndHashCode(callSuper = true)
class SearchListRequestDTO : PageableRequestDTO() {

    private val fromDate: String? = null

    private val toDate: String? = null

}