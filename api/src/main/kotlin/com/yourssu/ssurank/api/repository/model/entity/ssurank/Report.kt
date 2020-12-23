package com.yourssu.ssurank.api.repository.model.entity.ssurank

import com.yourssu.ssurank.api.repository.model.entity.common.SuperEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "reports")
data class Report(
        @field:Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override val id: Int? = null,

        @ManyToOne
        val professorEvaluation: ProfessorEvaluation,

        @ManyToOne
        val courseEvaluation: CourseEvaluation,

        @Column(columnDefinition = "TEXT")
        val content: String,

        @Column
        val reportedAt: LocalDateTime = LocalDateTime.now()
) : SuperEntity<Int>