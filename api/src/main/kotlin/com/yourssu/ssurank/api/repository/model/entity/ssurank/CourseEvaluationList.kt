package com.yourssu.ssurank.api.repository.model.entity.ssurank

import com.yourssu.ssurank.api.repository.model.entity.common.SuperEntity
import javax.persistence.*

@Entity
data class CourseEvaluationList(
        @field:Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override val id: Int? = null,

        @ManyToOne
        @JoinColumn(name = "courseId")
        val course: Course,

        @ManyToOne
        @JoinColumn(name = "courseEvaluationId")
        val courseEvaluation: CourseEvaluation
) : SuperEntity<Int>
