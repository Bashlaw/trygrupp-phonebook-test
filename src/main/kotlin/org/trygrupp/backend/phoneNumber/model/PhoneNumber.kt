package org.trygrupp.backend.phoneNumber.model

import lombok.*
import org.hibernate.annotations.Proxy
import org.springframework.beans.BeanUtils
import org.trygrupp.backend.phoneNumber.dto.PhoneNumberDTO
import javax.persistence.*

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Proxy(lazy = false)
class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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