package org.trygrupp.backend.general.service.implementation

import com.google.gson.Gson
import kong.unirest.HttpResponse
import kong.unirest.JsonNode
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.trygrupp.backend.exceptions.GeneralException
import org.trygrupp.backend.exceptions.RemoteServiceException
import org.trygrupp.backend.general.dto.Response
import org.trygrupp.backend.general.dto.ResponseConstants
import org.trygrupp.backend.general.service.GeneralService
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

@Slf4j
@Service
class GeneralServiceImpl(private val gson: Gson) : GeneralService {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Value("\${max-pull-size:100}")
    private val maxPullSize = 0

    //Used to format object into a string
    override fun getAsString(o: Any?): String? {
        return gson.toJson(o)
    }

    //used to format the return value of the http post or get request
    override fun getResponseAsString(response: HttpResponse<JsonNode?>?): String? {
        log.info("getting JSON response as a string")

        if (Objects.nonNull(response)) {
            if (Objects.nonNull(response!!.body)) {
                val body = response.body!!.toPrettyString()
                log.info(body)
                return body
            }
        }

        throw RemoteServiceException("No Response from Host")
    }

    override fun getResponseAsString(response: HttpResponse<JsonNode?>?, getStatus: Boolean): HashMap<Int?, String?>? {
        log.info("getting JSON response as a Map of body and status")

        val codeToResponse = HashMap<Int?, String?>()

        if (Objects.nonNull(response)) {
            val body = response!!.body!!.toPrettyString()
            log.info(body)
            codeToResponse[response.status] = body
            return codeToResponse
        }

        throw RemoteServiceException("No Response from Host")
    }

    override fun isStringInvalid(string: String?): Boolean {
        return Objects.isNull(string) || string!!.trim { it <= ' ' } == ""
    }

    override fun getAmountAsBigDecimal(amountString: String?, isKobo: Boolean): BigDecimal? {
        log.info("getting amount as decimal")
        val amount: BigDecimal
        if (Objects.isNull(amountString)) {
            throw GeneralException("Invalid Amount")
        }
        if (amountString == "" || amountString == "0") {
            throw GeneralException("Invalid Amount")
        } else {
            amount = BigDecimal(amountString)
        }
        return if (isKobo) {
            amount.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP)
        } else {
            amount
        }
    }

    override fun getPageableObject(size: Int, page: Int): Pageable? {

        var size = size
        var page = page
        log.info("Getting pageable object, initial size => {} and page {}", size, page)

        val paged: Pageable
        page -= 1
        if (page < 0) {
            throw GeneralException("Page minimum is 1")
        }
        if (size <= 0) {
            throw GeneralException("Size minimum is 1")
        }
        if (size > maxPullSize) {
            log.info("{} greater than max size of {}, defaulting to max", size, maxPullSize)
            size = maxPullSize
        }
        val sort = Sort.by(Sort.Direction.DESC, "id")
        paged = PageRequest.of(page, size, sort)
        return paged
    }

    //used to format failed response body
    override fun prepareFailedResponse(code: String?, status: String?, message: Any?): Response? {
        val response = Response()
        response.responseCode = code
        response.status = status
        response.data = message
        log.info("ResponseCode => {}, status => {} and message => {}", code, status, message)
        return response
    }

    override fun prepareSuccessResponse(data: Any?): Response? {
        val code: String = ResponseConstants.ResponseCode.SUCCESS
        val status: String = ResponseConstants.ResponseMessage.SUCCESS
        val response = Response()
        response.responseCode = code
        response.status = status
        response.data = data
        log.info("ResponseCode => {}, status => {} and data => {}", code, status, data)
        return response
    }

}