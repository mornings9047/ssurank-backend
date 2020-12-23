package com.yourssu.ssurank.api.repository.model.entity.ssurank

import com.yourssu.ssurank.api.repository.model.entity.common.SuperEntity
import javax.persistence.*

@Entity
data class ProfessorEvaluationList(
        @field:Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override val id: Int? = null,

        @ManyToOne
        @JoinColumn(name = "professorId")
        val professor: Professor,

        @ManyToOne
        @JoinColumn(name = "professorEvaluationId")
        val professorEvaluation: ProfessorEvaluation
) : SuperEntity<Int>