package org.trygrupp.backend.contact.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.trygrupp.backend.contact.model.Contact

interface ContactRepository : JpaRepository<Contact?, Long?> {

    fun existsByName(name: String?): Boolean

    fun existsByNameAndDelFlag(name: String?, delFlag: Boolean): Boolean

    fun findContactByName(name: String?): Contact?

}