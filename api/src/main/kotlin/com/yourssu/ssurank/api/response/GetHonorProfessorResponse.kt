package com.yourssu.ssurank.api.response

import com.yourssu.ssurank.api.repository.model.projection.ssurank.ProfessorTransporter

class GetHonorProfessorResponse(professorTransporter: ProfessorTransporter) {
    val name = professorTransporter.name
    val department = professorTransporter.department
    val position = professorTransporter.position
    val ranking = professorTransporter.ranking
}