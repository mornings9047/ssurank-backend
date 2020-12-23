package com.yourssu.ssurank.api.repository.model.entity.ssurank
import com.yourssu.ssurank.api.repository.model.entity.common.SuperEntity
import javax.persistence.*

@Entity
@Table(name = "course_professor")
data class CourseProfessor(
        @field:Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override val id: Int? = null,

        @ManyToOne
        @JoinColumn(name = "courseId")
        val course: Course?,

        @ManyToOne
        @JoinColumn(name = "professorId")
        val professor: Professor?
) : SuperEntity<Int>
