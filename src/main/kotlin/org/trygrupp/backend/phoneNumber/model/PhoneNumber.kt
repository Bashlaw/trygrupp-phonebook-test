package org.trygrupp.backend.phoneNumber.model

import lombok.*
import org.hibernate.annotations.Proxy
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
    val phoneNo: String? = null

}