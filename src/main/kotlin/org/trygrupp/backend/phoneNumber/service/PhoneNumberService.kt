package org.trygrupp.backend.phoneNumber.service

interface PhoneNumberService {

    fun add(phoneNo: String?)

    fun addList(phoneNo: List<String?>?)

    fun getPhoneNos(contactId: Long?): List<String?>?

}