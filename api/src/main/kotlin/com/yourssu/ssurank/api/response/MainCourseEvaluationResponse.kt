package com.yourssu.ssurank.api.response

import com.yourssu.ssurank.api.repository.model.projection.ssurank.MainCourseEvaluationTransporter

class MainCourseEvaluationResponse(
        val evaluations: List<MainCourseEvaluationTransporter>,
        override val message: String? = null
) : Response(message)
