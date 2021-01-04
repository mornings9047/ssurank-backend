package com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Department
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Semester

data class CreateCourseDto(
        val code: String,
        val year: Int,
        val semester: Semester,
        val title: String,
        val lectureGrade: Int,
        val professor: String,
        val college: String,
        val department: Department,
        val position: String,
        val rating: Float,

        val classification: String?,
        val target: String?,
        val major: String?
)