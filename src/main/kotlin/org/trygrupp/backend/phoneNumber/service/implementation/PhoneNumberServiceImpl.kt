package org.trygrupp.backend.phoneNumber.service.implementation

import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.trygrupp.backend.phoneNumber.model.PhoneNumber
import org.trygrupp.backend.phoneNumber.repositrory.PhoneNumberRepository
import org.trygrupp.backend.phoneNumber.service.PhoneNumberService

@Slf4j
@Service
@RequiredArgsConstructor
class PhoneNumberServiceImpl : PhoneNumberService {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Autowired
    lateinit var phoneNumberRepository: PhoneNumberRepository

    override fun add(phoneNo: String?) {
        log.info("adding phone number {}", phoneNo)

        val phoneNumber = PhoneNumber()
        phoneNumber.phoneNo = phoneNo

        //save to DB
        phoneNumberRepository.save(phoneNumber)
    }

    override fun addList(phoneNo: List<String?>?) {
        log.info("adding list of phone number {}", phoneNo)

        val phoneNumbers: MutableList<PhoneNumber> = ArrayList()
        for (phoneNumber in phoneNo!!) {
            val phoneNum = PhoneNumber()
            phoneNum.phoneNo = phoneNumber
            phoneNumbers.add(phoneNum)
        }

        //save all to DB
        phoneNumberRepository.saveAll(phoneNumbers)
    }

    override fun getPhoneNos(contactId: Long?): List<String?>? {
        return phoneNumberRepository.getPhoneNums(contactId)
    }

    override fun searchPhoneNo(phoneNo: String?): List<Long?>? {
        return phoneNumberRepository.getPhoneIds(phoneNo)
    }

}