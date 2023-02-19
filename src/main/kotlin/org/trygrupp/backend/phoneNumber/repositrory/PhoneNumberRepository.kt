package org.trygrupp.backend.phoneNumber.repositrory

import org.springframework.data.jpa.repository.JpaRepository
import org.trygrupp.backend.phoneNumber.model.PhoneNumber

interface PhoneNumberRepository : JpaRepository<PhoneNumber?, Long?>