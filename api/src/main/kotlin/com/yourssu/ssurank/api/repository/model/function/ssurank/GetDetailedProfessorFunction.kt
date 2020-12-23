package com.yourssu.ssurank.api.repository.model.function.ssurank

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.DetailedProfessorDto

class GetDetailedProfessorFunction(
        private val professorDataAccessor: ProfessorDataAccessor
) {
    fun getDetailedProfessor(id: Int): DetailedProfessorDto {
        val detailedProfessorTransporter = professorDataAccessor.getDetailedProfessor(id)
        val topPercent = professorDataAccessor.getTopPercent(id)
        val sessions = professorDataAccessor.getSessions(id)
        return DetailedProfessorDto(detailedProfessorTransporter, topPercent, sessions)
    }
}