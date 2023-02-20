package org.trygrupp.backend.contact.service

import org.trygrupp.backend.contact.dto.ContactDTO
import org.trygrupp.backend.contact.dto.ContactListDTO
import org.trygrupp.backend.contact.dto.ContactListRequestDTO
import org.trygrupp.backend.contact.dto.CreateContactDTO

interface ContactService {

    fun create(dto: CreateContactDTO?): ContactDTO?

    fun delete(name: String?): Boolean

    fun getSingle(name: String?): ContactDTO?

    fun getContacts(dto: ContactListRequestDTO): ContactListDTO?

}