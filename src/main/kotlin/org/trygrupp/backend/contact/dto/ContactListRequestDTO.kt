package org.trygrupp.backend.contact.dto

import lombok.Data
import lombok.EqualsAndHashCode
import org.trygrupp.backend.general.dto.SearchListRequestDTO

@Data
@EqualsAndHashCode(callSuper = true)
class ContactListRequestDTO : SearchListRequestDTO() {

    var name: String? = null

    var address: String? = null

    var phoneNumber: String? = null

}