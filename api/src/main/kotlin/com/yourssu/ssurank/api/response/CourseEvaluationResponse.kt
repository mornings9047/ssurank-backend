package com.yourssu.ssurank.api.response

import com.yourssu.ssurank.api.repository.model.projection.ssurank.CourseEvaluationTransporter

class CourseEvaluationResponse(
        val evaluations: List<CourseEvaluationTransporter>,
        override val message: String? = null
) : Response(message)