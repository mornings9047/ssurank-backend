package com.yourssu.ssurank.api.response

import com.yourssu.ssurank.api.repository.model.projection.ssurank.MainProfessorEvaluationTransporter

class MainProfessorEvaluationResponse(
        val evaluations: List<MainProfessorEvaluationTransporter>,
        override val message: String? = null
) : Response(message)