package org.trygrupp.backend.general.dto

import lombok.Data

@Data
class Response {

    var status: String? = null

    var responseCode: String? = null

    var failureReason: Any? = null

    var data: Any? = null

}