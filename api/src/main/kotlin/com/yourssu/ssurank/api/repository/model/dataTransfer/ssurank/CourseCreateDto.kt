package com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Semester

data class CourseCreateDto(
        val code: String,
        val year:Int,
        val semester: Semester,
        val title: String,
        val lectureGrade: Int,
        val professor: String,
        val college: String,
        val department: String,
        val position: String,
        var rating: Float,

        val classification: String,
        val target: String,
        val major: String

)

