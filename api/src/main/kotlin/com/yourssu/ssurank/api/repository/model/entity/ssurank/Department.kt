package com.yourssu.ssurank.api.repository.model.entity.ssurank

import com.yourssu.ssurank.api.repository.model.entity.common.SuperEntity
import javax.persistence.*

@Entity
@Table(name = "departments")
data class Department(
        @field:Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override val id: Int? = null,

        @Column(length = 7)
        val college: String,

        @Column(length = 20)
        val original_name: String,

        @Column(length = 6)
        val shortened_name: String
) : SuperEntity<Int>