package org.trygrupp.backend.exceptions

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.trygrupp.backend.general.dto.Response
import org.trygrupp.backend.general.dto.ResponseConstants
import org.trygrupp.backend.general.service.GeneralService
import java.util.stream.Collectors

@ControllerAdvice
class ExceptionController(private val generalService: GeneralService) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(GeneralException::class, RemoteServiceException::class)
    fun handleException(ex: Exception): ResponseEntity<*> {
        logger.info("Error occurred, error message is {}", ex.message)

        val response: Response? = generalService.prepareFailedResponse(
            ResponseConstants.ResponseCode.FAILED,
            ResponseConstants.ResponseMessage.FAILED,
            ex.message
        )
        return ResponseEntity(response, HttpStatus.OK)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<*> {
        logger.info("Error occurred during request body validation, error message is {}", ex.message)

        //Get all errors
        val errors = ex.bindingResult
            .fieldErrors
            .stream()
            .map { obj: FieldError -> obj.defaultMessage }
            .collect(Collectors.toList())
        val response: Response? = generalService.prepareFailedResponse(
            ResponseConstants.ResponseCode.FAILED,
            ResponseConstants.ResponseMessage.FAILED,
            errors
        )
        return ResponseEntity(response, HttpStatus.OK)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: Exception): ResponseEntity<*> {
        logger.info("Error occurred, error message is {}", ex.message)

        val response: Response? = generalService.prepareFailedResponse(
            ResponseConstants.ResponseCode.FAILED,
            ResponseConstants.ResponseMessage.FAILED,
            ex.message
        )
        return ResponseEntity(response, HttpStatus.NOT_FOUND)
    }

}