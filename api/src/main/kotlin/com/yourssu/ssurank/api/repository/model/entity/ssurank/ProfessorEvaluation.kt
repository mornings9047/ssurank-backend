package com.yourssu.ssurank.api.repository.model.entity.ssurank

import com.yourssu.ssurank.api.repository.model.entity.common.SuperEntity
import org.hibernate.annotations.Where
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "professor_evaluations")
@Where(clause = "is_deleted = 0")
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
        var thumbsUp: Int = 0,

        @Column
        var thumbsDown: Int = 0,

        @Column
        val createdAt: LocalDateTime = LocalDateTime.now(),

        @Column
        var isDeleted: Boolean = false
) : SuperEntity<Int>