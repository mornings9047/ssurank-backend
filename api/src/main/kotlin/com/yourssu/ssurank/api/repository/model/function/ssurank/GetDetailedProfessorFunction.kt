package com.yourssu.ssurank.api.repository.model.function.ssurank

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.DetailedProfessorDto
import com.yourssu.ssurank.api.repository.model.projection.ssurank.DetailedProfessorTransporter
import reactor.core.publisher.Mono

class GetDetailedProfessorFunction(
        private val professorDataAccessor: ProfessorDataAccessor
) {
    fun getDetailedProfessor(name: String): DetailedProfessorDto {
        val detailedProfessorTransporter = professorDataAccessor.getDetailedProfessor(name)
        val topPercent = professorDataAccessor.getTopPercent(name)
        val sessions = professorDataAccessor.getSessions(name)
        return DetailedProfessorDto(detailedProfessorTransporter, topPercent, sessions)
    }
}