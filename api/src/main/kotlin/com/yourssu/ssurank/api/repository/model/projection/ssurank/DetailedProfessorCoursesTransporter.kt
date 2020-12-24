package com.yourssu.ssurank.api.repository.model.projection.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Ranking
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Semester

interface DetailedProfessorCoursesTransporter {
    val courseId: Int
    val ranking: Ranking
    val name: String
    val department: String
    val title: String
    val year: Int
    val semester: Semester
}