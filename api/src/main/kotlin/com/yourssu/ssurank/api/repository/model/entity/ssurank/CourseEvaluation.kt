package com.yourssu.ssurank.api.repository.model.entity.ssurank

import com.yourssu.ssurank.api.repository.model.entity.common.SuperEntity
import org.hibernate.annotations.Where
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "course_evaluations")
@Where(clause = "is_deleted = 0")
data class CourseEvaluation(
        @field:Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override val id: Int? = null,

        @Column(nullable = false)
        val email: String,

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        val studentType: StudentType,

        @Column(nullable = false)
        val year: Int,

        @Column(nullable = false)
        val semester: Semester,

        @Column(columnDefinition = "TEXT")
        val content: String,

        @Column(nullable = false)
        var thumbs_up: Int = 0,

        @Column(nullable = false)
        var thumbs_down: Int = 0,

        @Column(nullable = true)
        val createAt: LocalDateTime = LocalDateTime.now(),

        @Column(nullable = false)
        var isDeleted: Boolean = false
) : SuperEntity<Int>