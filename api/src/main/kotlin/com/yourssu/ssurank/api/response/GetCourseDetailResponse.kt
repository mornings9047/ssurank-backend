package com.yourssu.ssurank.api.response

import com.yourssu.ssurank.api.repository.model.projection.ssurank.CourseTransporter

class GetCourseDetailResponse(
        val course: CourseTransporter,
        override val message: String? = null
) : Response(message)