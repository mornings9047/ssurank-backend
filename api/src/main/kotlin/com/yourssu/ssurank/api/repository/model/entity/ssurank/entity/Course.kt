package com.yourssu.ssurank.api.repository.model.entity.ssurank.entity

import com.yourssu.ssurank.api.repository.model.entity.common.SuperEntity
import org.hibernate.annotations.ColumnDefault
import javax.persistence.*

@Entity
@Table(name = "courses")
data class Course(
        @field:Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override val id: Int? = null,

        @Column(nullable = false)
        val title: String,

        @Column(nullable = false)
        val major: String,

        @ManyToOne
        @JoinColumn(name = "professorId")
        var professor: Professor?,

        @Column(nullable = false)
        val year: Int,

        @Column(nullable = false)
        val semester: Semester,

        @Column(nullable = false)
        @ColumnDefault("0")
        val lectureGrade: Int?,

        @Column(nullable = false, length = 15)
        @ColumnDefault("0")
        val code: String?,

        @Column(nullable = false)
        val classification: String,

        @Column(nullable = false)
        val target: String,

        @Column(nullable = false)
        val rating: Float?
) : SuperEntity<Int>