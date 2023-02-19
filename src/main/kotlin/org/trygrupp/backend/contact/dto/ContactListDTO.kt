package org.trygrupp.backend.contact.dto

import lombok.Data
import lombok.EqualsAndHashCode
import org.trygrupp.backend.general.dto.PageableResponseDTO

@Data
@EqualsAndHashCode(callSuper = true)
class ContactListDTO : PageableResponseDTO() {

    var contactDTOs: List<ContactDTO>? = null

}