package com.yourssu.ssurank.api.repository.model.projection.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Semester

interface SessionCourseTransporter {
    val year: Int
    val semester: Semester
}