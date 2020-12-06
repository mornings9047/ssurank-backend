package com.yourssu.ssurank.api.repository.model.projection.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Professor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Semester

interface SearchCourseTransporter{
    val title: String
    val year: Int
    val semester: Semester
    val professor: Professor
}