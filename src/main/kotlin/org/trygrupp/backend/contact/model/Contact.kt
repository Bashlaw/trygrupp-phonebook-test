package org.trygrupp.backend.contact.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import lombok.*
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(unique = true, nullable = false)
    var name: String? = null

    var address: String? = null

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ToString.Exclude
    @JsonManagedReference
    var phoneNumbers: List<PhoneNumber>? = null

    var delFlag = false

}