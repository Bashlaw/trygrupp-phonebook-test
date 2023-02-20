package org.trygrupp.backend.contact.controller

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.trygrupp.backend.contact.dto.ContactListRequestDTO
import org.trygrupp.backend.contact.dto.CreateContactDTO
import org.trygrupp.backend.contact.service.ContactService
import org.trygrupp.backend.general.dto.Response
import org.trygrupp.backend.general.service.GeneralService
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/contact")
@RequiredArgsConstructor
class ContactController {

    private val contactService: ContactService? = null

    private val generalService: GeneralService? = null

//    @PostMapping("/create")
//    fun createContact(@RequestBody dto: @Valid CreateContactDTO?): Response? {
//        val data = contactService!!.create(dto)
//        return generalService!!.prepareSuccessResponse(data)
//    }

    @DeleteMapping("/delete/{contactName}")
    fun deleteContact(@PathVariable contactName: @Valid String?): Response? {
        return if (contactService!!.delete(contactName)) generalService!!.prepareSuccessResponse("Delete Successfully") else generalService!!.prepareSuccessResponse(
            "Error found"
        )
    }

//    @GetMapping("/single/{phoneNumber}")
//    fun getSingle(@PathVariable phoneNumber: @Valid String?): Response? {
//        //val data = contactService!!.getSingle(phoneNumber)
//        println(phoneNumber);
//        return generalService!!.prepareSuccessResponse(contactService?.getSingle(phoneNumber))
//    }
//
//    @GetMapping("/multiple")
//    fun getMultiple(@RequestBody dto: @Valid ContactListRequestDTO?): Response? {
//        val data = contactService!!.getContacts(dto)
//        return generalService!!.prepareSuccessResponse(data)
//    }

    @GetMapping("/test")
    fun getTest(
        @PathVariable("phoneNumber") phoneNumber: String,
    ) = ResponseEntity.ok(contactService?.getSingle(phoneNumber))

}