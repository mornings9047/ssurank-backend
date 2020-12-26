package com.yourssu.ssurank.api.response

import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.DetailedCourseDto

data class DetailedCourseResponse(
        val detailedCourse: DetailedCourseDto,
        override val message: String? = null
) : Response(message)