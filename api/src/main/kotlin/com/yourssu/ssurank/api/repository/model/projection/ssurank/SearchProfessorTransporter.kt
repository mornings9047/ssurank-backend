package com.yourssu.ssurank.api.repository.model.projection.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Course

interface SearchProfessorTransporter {
    val name: String
    val department: String
    val position: String
    val ranking: String
    val courses: Collection<Course>?
}