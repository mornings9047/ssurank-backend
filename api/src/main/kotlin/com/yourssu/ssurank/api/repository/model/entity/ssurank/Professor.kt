package com.yourssu.ssurank.api.repository.model.entity.ssurank

import com.yourssu.ssurank.api.repository.model.entity.common.SuperEntity
import javax.persistence.*

@Entity
@Table(name = "professors")
data class Professor(
    @field:Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Int? = null,

    @Column(length = 30, nullable = false)
    val name: String,

    @Column(length = 20, nullable = true)
    val college: String,

    @OneToOne(cascade = [CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.DETACH])
    @JoinColumn(name = "department_id")
    val department: Department,

    @Column(length = 20, nullable = true)
    val position: String,

    @Column(nullable = false)
    var rating: Float = 0F,

    @Column(length = 2, nullable = true)
    @Enumerated(EnumType.STRING)
    var ranking: Ranking = Ranking.U0
) : SuperEntity<Int>