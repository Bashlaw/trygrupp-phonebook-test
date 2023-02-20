package org.trygrupp.backend.customSearch

import org.springframework.data.domain.Page
import org.trygrupp.backend.contact.dto.ContactListRequestDTO
import org.trygrupp.backend.contact.model.Contact

interface CustomSearchService {

    fun searchContact(dto: ContactListRequestDTO?): Page<Contact?>?

}