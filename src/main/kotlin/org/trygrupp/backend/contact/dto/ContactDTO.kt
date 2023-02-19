package org.trygrupp.backend.contact.dto

import lombok.Data

@Data
class ContactDTO {

    var name: String? = null

    var address: String? = null

    var phoneNumber: List<String>? = null

}