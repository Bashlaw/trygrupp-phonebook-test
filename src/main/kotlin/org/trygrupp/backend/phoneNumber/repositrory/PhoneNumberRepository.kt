package org.trygrupp.backend.phoneNumber.repositrory

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.trygrupp.backend.phoneNumber.model.PhoneNumber

interface PhoneNumberRepository : JpaRepository<PhoneNumber?, Long?> {

    @Query(
        value = "select p.phone_no from phone_number p join contact_phone_numbers c on p.id = c.phone_numbers_id where  c.contact_id = ?1",
        nativeQuery = true
    )
    fun getPhoneNums(contactId: Long?): List<String?>?

    @Query(
        value = "select p.id from phone_number p where lower(p.phone_no) like ('%' || lower(:searchText) || '%')",
        nativeQuery = true
    )
    fun getPhoneIds(searchText: String?): List<Long?>?

}