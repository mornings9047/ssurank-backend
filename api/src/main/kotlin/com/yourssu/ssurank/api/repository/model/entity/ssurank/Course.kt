package com.yourssu.ssurank.api.repository.model.entity.ssurank

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

        @Column(nullable = true)
        val major: String?,

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

        @Column(nullable = true)
        val classification: String?,

        @Column(nullable = true)
        val target: String?,

        @Column(nullable = true)
        val rating: Float = 0.0F,

        @Column(length = 2, nullable = true)
        var ranking: Ranking = Ranking.D0

) : SuperEntity<Int>