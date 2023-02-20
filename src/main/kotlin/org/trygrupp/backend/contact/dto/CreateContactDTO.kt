package org.trygrupp.backend.contact.dto

import lombok.Data

@Data
class CreateContactDTO {

    var name: String? = null

    var address: String? = null

    var phoneNumbers: Set<String>? = null

}