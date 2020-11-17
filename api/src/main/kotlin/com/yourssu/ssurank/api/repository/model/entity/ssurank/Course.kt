package com.yourssu.ssurank.api.repository.model.entity.ssurank

import com.yourssu.ssurank.api.repository.model.entity.common.SuperEntity
import org.hibernate.annotations.ColumnDefault
import javax.persistence.*

@Entity
@Table(name = "courses")
data class Course(
        @field:Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override val id: Int?,

        @Column(nullable = false, length = 75)
        val title: String,

        @OneToMany
        @JoinColumn(name = "professorId")
        val professor: Collection<CourseProfessor>,

        @Column(nullable = false)
        val year: Int,

        @Column(nullable = false)
        val semester: Semester,

        @Column(nullable = false)
        @ColumnDefault("0")
        val credit: Int,

        @Column(nullable = false, length = 15)
        @ColumnDefault("0")
        val code: String,

        @Column(nullable = false)
        val classification: Classification,

        @Column(nullable = false)
        val grade: Grade,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "categoryId", nullable = false)
        val category: CourseCategory,

        @Column(nullable = false)
        val target: String,

        @Column(nullable = false)
        val maximum: String,

        @Column(nullable = false)
        val unitTime: Int,

        @Column(nullable = false)
        val rating: Float
) : SuperEntity<Int>