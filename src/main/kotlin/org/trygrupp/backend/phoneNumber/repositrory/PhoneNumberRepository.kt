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
        value = "select cpn.contact_id from phone_number p join contact_phone_numbers cpn on p.id = cpn.phone_numbers_id where lower(p.phone_no) like ('%' || lower(:searchText) || '%')",
        nativeQuery = true
    )
    fun getPhoneIds(searchText: String?): List<Long?>?

}