package org.trygrupp.backend.contact.dto

import lombok.Data
import lombok.EqualsAndHashCode
import org.trygrupp.backend.general.dto.SearchListRequestDTO

@Data
@EqualsAndHashCode(callSuper = true)
class ContactListRequestDTO : SearchListRequestDTO() {

    lateinit var name: String

    lateinit var address: String

    lateinit var phoneNumber: String

}