package org.trygrupp.backend.customSearch.implementation

import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.trygrupp.backend.contact.dto.ContactListRequestDTO
import org.trygrupp.backend.contact.model.Contact
import org.trygrupp.backend.customSearch.CustomSearchService
import org.trygrupp.backend.phoneNumber.service.PhoneNumberService
import org.trygrupp.backend.utils.DateUtil.atEndOfDay
import org.trygrupp.backend.utils.DateUtil.atStartOfDay
import org.trygrupp.backend.utils.DateUtil.dateTimeFullFormat
import org.trygrupp.backend.utils.DateUtil.dateToLocalDateTime
import org.trygrupp.backend.utils.DateUtil.todayDate
import org.trygrupp.backend.utils.DateUtil.todayDateInAnyYear
import org.trygrupp.backend.utils.GeneralUtil.stringIsNullOrEmpty
import java.time.LocalDateTime
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TypedQuery
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Path
import javax.persistence.criteria.Predicate

@Slf4j
@Service
@RequiredArgsConstructor
class CustomSearchServiceIml : CustomSearchService {

    private val log = LoggerFactory.getLogger(this::class.java)

    private val phoneNumberService: PhoneNumberService? = null

    @PersistenceContext
    private val em: EntityManager? = null

    override fun searchContact(dto: ContactListRequestDTO?): Page<Contact?>? {
        log.info("search through contact...")

        val cb = em!!.criteriaBuilder
        val cq = cb.createQuery(
            Contact::class.java
        )

        val root = cq.from(
            Contact::class.java
        )
        val predicates: MutableList<Predicate> = ArrayList()

        if (valid(dto!!.name)) {
            predicates.add(cb.like(cb.lower(root.get("name")), '%'.toString() + dto.name!!.lowercase() + '%'))
        }

        if (valid(dto.address)) {
            predicates.add(cb.like(cb.lower(root.get("address")), '%'.toString() + dto.address!!.lowercase() + '%'))
        }

        //see if search phone number exist
        val phoneIds = phoneNumberService!!.searchPhoneNo(dto.phoneNumber)!!
        if (phoneIds.isNotEmpty()) {
            for (id in phoneIds) {
                predicates.add(cb.equal(root.get<Any>("phoneNumbersId"), id))
            }
        }

        verifyQueryDate(cb, predicates, dto.fromDate, dto.toDate, root.get("createdAt"))

        //cq.where(*predicates.toArray<Predicate>(arrayOf<Predicate>()))
        cq.where(*predicates.toTypedArray())
        cq.orderBy(cb.desc(root.get<Any>("createdAt")))
        val query: TypedQuery<*> = em.createQuery(cq)

        return getPage(dto.page, dto.size, query) as Page<Contact?>
    }

    private fun valid(value: String?): Boolean {
        return !stringIsNullOrEmpty(value!!)
    }

    private fun getDate(dateString: String?): Date? {
        return dateTimeFullFormat(dateString!!)
    }

    private fun getPage(page: Int, size: Int, query: TypedQuery<*>): PageImpl<*> {
        log.info("getting page...")

        var page = page
        val paged: Pageable

        if (page > 0) {
            page -= 1
        }

        val totalRows: Int = query.resultList.size
        paged = PageRequest.of(page, size)
        query.firstResult = paged.getPageNumber() * paged.getPageSize()
        query.maxResults = paged.getPageSize()

        return PageImpl(query.resultList, paged, totalRows.toLong())
    }

    private fun verifyQueryDate(
        cb: CriteriaBuilder,
        predicates: MutableList<Predicate>,
        fromDate: String?,
        toDate: String?,
        queryDate: Path<LocalDateTime>
    ) {

        if (valid(fromDate) && valid(toDate)) {
            val dateBefore = atStartOfDay(getDate(fromDate)!!)
            val dateAfter = atEndOfDay(getDate(toDate)!!)
            predicates.add(cb.between(queryDate, dateToLocalDateTime(dateBefore), dateToLocalDateTime(dateAfter)))
        }

        if (valid(fromDate) && !valid(toDate)) {
            val dateBefore = atStartOfDay(getDate(fromDate)!!)
            val dateAfter = atEndOfDay(todayDate()!!)
            predicates.add(cb.between(queryDate, dateToLocalDateTime(dateBefore), dateToLocalDateTime(dateAfter)))
        }

        if (!valid(fromDate) && !valid(toDate)) {
            val dateBefore = atStartOfDay(todayDateInAnyYear(-10)!!)
            val dateAfter = atEndOfDay(todayDate()!!)
            predicates.add(cb.between(queryDate, dateToLocalDateTime(dateBefore), dateToLocalDateTime(dateAfter)))
        }

    }

}