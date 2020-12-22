package com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank

import com.yourssu.ssurank.api.repository.model.projection.ssurank.DetailedProfessorTransporter
import com.yourssu.ssurank.api.repository.model.projection.ssurank.SessionCourseTransporter

class DetailedProfessorDto(
        detailedProfessorTransporter: DetailedProfessorTransporter,
        topPercent: Int,
        sessions: List<SessionCourseTransporter>
) {
    val id = detailedProfessorTransporter.id
    val name = detailedProfessorTransporter.name
    val department = detailedProfessorTransporter.department
    val position = detailedProfessorTransporter.position
    val ranking = detailedProfessorTransporter.ranking
    val topPercent = topPercent
    val courseCnt = detailedProfessorTransporter.courseCnt
    val sessions = sessions
}