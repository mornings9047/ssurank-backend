package com.yourssu.ssurank.api.repository.model.entity.ssurank

import com.yourssu.ssurank.api.repository.model.entity.common.SuperEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "Course_Evaluations")
data class CourseEvaluation(
        @field:Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override val id: Int? = null,

        @Column(nullable = false)
        val email: String,

        @Column(nullable = false)
        val studentType: StudentType,

        @Column(nullable = false)
        val year: Int,

        @Column(nullable = false)
        val semester: Semester,

        @Column(nullable = false)
        val content: String,

        @Column(nullable = false)
        val thumbs_up : Int = 0,

        @Column(nullable = false)
        val thumbs_down : Int = 0,

        @Column(nullable = true)
        val createAt: LocalDateTime = LocalDateTime.now(),

        @Column(nullable = false)
        val isDeleted: Boolean? = false
) : SuperEntity<Int>

