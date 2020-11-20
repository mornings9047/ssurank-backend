package com.yourssu.ssurank.api.repository.model.entity.ssurank

import com.yourssu.ssurank.api.repository.model.entity.common.SuperEntity
import javax.persistence.*

@Entity
@Table(name = "professors")
data class Professor(
        @field:Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override val id: Int?,

        @Column(length = 20, nullable = false)
        val name: String,

        @Column(length = 20, nullable = true)
        val department: String?,

        @Column(nullable = false)
        val rating: Float,

        @OneToMany
        @JoinColumn(name = "courseId")
        val courses: Collection<CourseProfessor>
) : SuperEntity<Int>