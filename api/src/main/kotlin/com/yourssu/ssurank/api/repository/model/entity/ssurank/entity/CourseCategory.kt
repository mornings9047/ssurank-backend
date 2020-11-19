package com.yourssu.ssurank.api.repository.model.entity.ssurank.entity

import com.yourssu.ssurank.api.repository.model.entity.common.SuperEntity
import javax.persistence.*

@Entity
@Table(name = "course_categories")
data class CourseCategory(
        @field:Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override val id: Int?,

        @OneToMany(fetch = FetchType.EAGER)
        @JoinColumn(name = "parentId", nullable = true)
        val children: List<CourseCategory>?,

        @Column(nullable = false)
        val name: String,

        @Column(nullable = false)
        val displayOrder: Int
) : SuperEntity<Int>