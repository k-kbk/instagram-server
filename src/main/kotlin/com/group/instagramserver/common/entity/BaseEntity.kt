package com.group.instagramserver.common.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.MIN,

    @LastModifiedDate
    @Column(nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.MIN
)