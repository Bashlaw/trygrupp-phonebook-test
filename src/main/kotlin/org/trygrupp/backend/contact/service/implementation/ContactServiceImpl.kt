package org.trygrupp.backend.contact.service.implementation

import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.trygrupp.backend.contact.dto.ContactDTO
import org.trygrupp.backend.contact.dto.ContactListDTO
import org.trygrupp.backend.contact.dto.ContactListRequestDTO
import org.trygrupp.backend.contact.dto.CreateContactDTO
import org.trygrupp.backend.contact.model.Contact
import org.trygrupp.backend.contact.repository.ContactRepository
import org.trygrupp.backend.contact.service.ContactService
import org.trygrupp.backend.customSearch.CustomSearchService
import org.trygrupp.backend.exceptions.GeneralException
import org.trygrupp.backend.phoneNumber.model.PhoneNumber
import org.trygrupp.backend.phoneNumber.service.PhoneNumberService
import java.time.LocalDateTime
import java.util.*
import java.util.stream.Collectors

@Slf4j
@Service
@RequiredArgsConstructor
class ContactServiceImpl : ContactService {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Autowired
    lateinit var contactRepository: ContactRepository

    @Autowired
    lateinit var phoneNumberService: PhoneNumberService

    @Autowired
    lateinit var customSearchService: CustomSearchService

    override fun create(dto: CreateContactDTO?): ContactDTO? {
        log.info("adding contact.... {}", dto)

        //perform checks before creation
        createChecks(dto)
        val contact = Contact()
        contact.address = dto!!.address
        contact.delFlag = false
        contact.name = dto.name

        //set phone numbers
        val phoneNos: Set<String?> = dto.phoneNumbers!!
        val phoneNoList: List<String?> = ArrayList(phoneNos)

        //save phone numbers
        phoneNumberService.addList(phoneNoList)
        val phoneNumbers: MutableList<PhoneNumber> = ArrayList()
        for (phoneNumber in phoneNos) {
            //save phone numbers
            val number = PhoneNumber()
            number.phoneNo = phoneNumber
            phoneNumbers.add(number)
        }
        contact.phoneNumbers = phoneNumbers
        return Contact.getContactDTO(contact)
    }

    override fun delete(name: String?): Boolean {
        log.info("deleting contact.... {}", name)

        //check if contact exist and not yet delete
        if (!contactRepository.existsByNameAndDelFlag(name, true)) {
            throw GeneralException("contact not found!")
        }

        val contact = contactRepository.findContactByName(name)!!
        contact.delFlag = true
        contact.updatedAt = LocalDateTime.now()

        //save to DB
        contactRepository.save(contact)
        return true
    }

    override fun getSingle(name: String?): ContactDTO? {

        if (!contactRepository.existsByName(name)) {
            throw GeneralException("contact not found!")
        }

        return Contact.getContactDTO(
            Objects.requireNonNull<Contact?>(
                contactRepository.findContactByName(name)
            )
        )
    }

    override fun getContacts(dto: ContactListRequestDTO): ContactListDTO? {
        val contactPage = customSearchService.searchContact(dto)
        return getContactListDTO(contactPage)
    }

    private fun getContactListDTO(contactPage: Page<Contact?>?): ContactListDTO {
        log.info("Converting terminal request page to terminal request list dto")

        val listDTO = ContactListDTO()
        var contactList = contactPage!!.content

        //filter by not deleted
        contactList = contactList.stream().filter { contact: Contact? -> !contact!!.delFlag }
            .collect(Collectors.toList())

        if (contactList.size > 0) {
            listDTO.hasNextRecord = contactPage.hasNext()
            listDTO.totalCount = contactPage.totalElements.toInt()
        }
        val contactDTOs = contactList.stream().map { contact -> contact?.let { Contact.getContactDTO(it) } }
            .collect(Collectors.toList())
        listDTO.contactDTOs = contactDTOs
        return listDTO
    }

    fun createChecks(dto: CreateContactDTO?) {
        log.info("performing checks....")

        //checking phone number is not null
        if (Objects.isNull(dto!!.phoneNumbers)) {
            throw GeneralException("phone number must not be provided!")
        }

        //checking phone number size
        if (dto.phoneNumbers?.size!! > 10) {
            throw GeneralException("phone number must not be more than 10!")
        }

        //checking if name does not already exist
        if (contactRepository.existsByName(dto.name)) {
            throw GeneralException("name already exits for another contact!")
        }

    }

}