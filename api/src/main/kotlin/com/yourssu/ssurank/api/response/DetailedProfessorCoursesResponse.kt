package com.yourssu.ssurank.api.response

import com.yourssu.ssurank.api.repository.model.projection.ssurank.DetailedProfessorCoursesTransporter

data class DetailedProfessorCoursesResponse(
        val detailedCourses: List<DetailedProfessorCoursesTransporter>,
        override val message: String? = null
) : Response(message)