package com.yourssu.ssurank.api.repository.model.entity.ssurank
import com.yourssu.ssurank.api.repository.model.entity.common.SuperEntity
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "courseEvaluation_list")
data class CourseEvaluationList(
        @field:Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override val id: Int? = null,

        @ManyToOne
        @JoinColumn(name = "courseId")
        val course: Course?,

        @ManyToOne
        @JoinColumn(name = "courseevaluationId")
        val courseEvaluation: CourseEvaluation?
) : SuperEntity<Int>
