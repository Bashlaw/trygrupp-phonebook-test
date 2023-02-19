package org.trygrupp.backend.contact.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import lombok.*
import org.springframework.beans.BeanUtils
import org.trygrupp.backend.contact.dto.ContactDTO
import org.trygrupp.backend.phoneNumber.model.PhoneNumber
import org.trygrupp.backend.utils.BaseEntity
import javax.persistence.*

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
class Contact : BaseEntity() {

    @Id
    @Column(unique = true, nullable = false)
    var id: Long? = null

    @Column(unique = true, nullable = false)
    var name: String? = null

    var address: String? = null

    @OneToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference
    var phoneNumbers: List<PhoneNumber>? = null

    var delFlag = false

    companion object {
        fun getContactDTO(contact: Contact): ContactDTO {
            val contactDTO = ContactDTO()
            BeanUtils.copyProperties(contact, contactDTO)

            //set phone number
            val phoneNumberList: List<PhoneNumber>? = contact.phoneNumbers
            val phoneList: List<String>? = emptyList()
            if (phoneNumberList != null) {
                for (phoneNumber in phoneNumberList) {
                    phoneList?.plus(phoneNumber.phoneNo)
                }
            }
            contactDTO.phoneNumber = phoneList
            return contactDTO
        }
    }

}