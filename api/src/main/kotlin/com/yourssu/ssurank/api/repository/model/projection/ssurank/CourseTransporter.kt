package com.yourssu.ssurank.api.repository.model.projection.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Semester

interface CourseTransporter {
    val title: String
    val year: Int
    val semester: Semester
    val professor: String
}