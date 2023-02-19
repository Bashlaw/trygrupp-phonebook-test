package org.trygrupp.backend.general.service

import kong.unirest.HttpResponse
import kong.unirest.JsonNode
import org.springframework.data.domain.Pageable
import org.trygrupp.backend.general.dto.Response
import java.math.BigDecimal

interface GeneralService {

    fun getAsString(o: Any?): String?

    //used to format the return value of the http post or get request
    fun getResponseAsString(response: HttpResponse<JsonNode?>?): String?

    fun getResponseAsString(response: HttpResponse<JsonNode?>?, getStatus: Boolean): HashMap<Int?, String?>?

    fun isStringInvalid(string: String?): Boolean

    fun getAmountAsBigDecimal(amountString: String?, isKobo: Boolean): BigDecimal?

    fun getPageableObject(size: Int, page: Int): Pageable?

    //used to format failure response body
    fun prepareFailedResponse(code: String?, status: String?, message: Any?): Response?

    //used to format success response body
    fun prepareSuccessResponse(data: Any?): Response?

}