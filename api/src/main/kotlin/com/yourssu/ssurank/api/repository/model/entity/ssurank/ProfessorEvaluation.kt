package com.yourssu.ssurank.api.repository.model.entity.ssurank

import com.yourssu.ssurank.api.repository.model.entity.common.SuperEntity
import org.hibernate.annotations.Where
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Where(clause = "is_deleted = false")
data class ProfessorEvaluation(
        @field:Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override val id: Int? = null,

        @Column(nullable = false)
        val email: String,

        @Column
        @Enumerated(EnumType.STRING)
        val studentType: StudentType,

        @Column(columnDefinition = "TEXT")
        val content: String,

        @Column
        val thumbsUp: Int = 0,

        @Column
        val thumbsDown: Int = 0,

        @Column
        val createdAt: Date = Date(),

        @Column
        val isDeleted: Boolean = false
) : SuperEntity<Int>