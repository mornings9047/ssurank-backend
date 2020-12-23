package com.yourssu.ssurank.api.request

class CourseEvaluationRequest(
        val courseId: Int,
        val email: String,
        val type: String,
        val year: Int,
        val semester: Int,
        val content: String
)