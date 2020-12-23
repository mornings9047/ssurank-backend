package com.yourssu.ssurank.api.repository.model.entity.ssurank

import com.yourssu.ssurank.api.repository.model.entity.common.SuperEntity
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Table(name = "Course_Evaluation")
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
        val thumbs_up : Int,

        @Column(nullable = false)
        val thumbs_down : Int,

        @Column(nullable = false)
        val createAt: Date,

        @Column(nullable = false)
        val isDeleted: Boolean? = null
) : SuperEntity<Int>

