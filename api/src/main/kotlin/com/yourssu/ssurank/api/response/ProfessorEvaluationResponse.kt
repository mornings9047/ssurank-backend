package com.yourssu.ssurank.api.response

import com.yourssu.ssurank.api.repository.model.projection.ssurank.ProfessorEvaluationTransporter

class ProfessorEvaluationResponse(
        val evaluations: List<ProfessorEvaluationTransporter>,
        override val message: String? = null
) : Response(message)