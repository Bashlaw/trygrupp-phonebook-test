package org.trygrupp.backend.phoneNumber.model

import lombok.*
import org.hibernate.annotations.Proxy
import org.springframework.beans.BeanUtils
import org.trygrupp.backend.phoneNumber.dto.PhoneNumberDTO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Proxy(lazy = false)
class PhoneNumber {

    @Id
    @Column(unique = true, nullable = false)
    val id: Long? = null

    @Column(nullable = false)
    var phoneNo: String? = null

    companion object {
        fun getPhoneNumberDTO(phoneNumber: PhoneNumber): PhoneNumberDTO {
            val phoneNumberDTO = PhoneNumberDTO()
            BeanUtils.copyProperties(phoneNumber, phoneNumberDTO)

            return phoneNumberDTO;
        }
    }

}