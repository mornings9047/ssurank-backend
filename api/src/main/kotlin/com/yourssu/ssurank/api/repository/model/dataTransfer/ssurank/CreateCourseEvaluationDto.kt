package com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Semester

class CreateCourseEvaluationDto(
        val email : String,
        val year: Int,
        val semester: Semester,
        val content: String
)