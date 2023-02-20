package org.trygrupp.backend.contact.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.trygrupp.backend.contact.dto.ContactListRequestDTO
import org.trygrupp.backend.contact.dto.CreateContactDTO
import org.trygrupp.backend.contact.service.ContactService
import org.trygrupp.backend.general.dto.Response
import org.trygrupp.backend.general.service.GeneralService
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/contact")
class ContactController {

    @Autowired
    lateinit var contactService: ContactService

    @Autowired
    lateinit var generalService: GeneralService

    @PostMapping("/create")
    fun createContact(@RequestBody dto: @Valid CreateContactDTO?): Response? {
        val data = contactService.create(dto)
        return generalService.prepareSuccessResponse(data)
    }

    @DeleteMapping("/delete/{contactName}")
    fun deleteContact(@PathVariable("contactName") contactName: @Valid String?): Response? {

        contactService.delete(contactName)

        return generalService.prepareSuccessResponse("Delete Successfully")
    }

    @GetMapping("/single/{phoneNumber}")
    fun getSingle(@PathVariable("phoneNumber") phoneNumber: @Valid String?): Response? {
        val data = contactService.getSingle(phoneNumber)
        return generalService.prepareSuccessResponse(data)
    }

    @PutMapping("/multiple")
    fun getMultiple(@RequestBody dto: @Valid ContactListRequestDTO?): Response? {
        val data = contactService.getContacts(dto)
        return generalService.prepareSuccessResponse(data)
    }

}