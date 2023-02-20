package org.trygrupp.backend.utils

import lombok.Getter
import lombok.RequiredArgsConstructor
import lombok.Setter
import lombok.ToString
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
abstract class BaseEntity {

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    var createdAt: LocalDateTime? = null

    @UpdateTimestamp
    var updatedAt: LocalDateTime? = null

}