package com.yourssu.ssurank.api.repository.model.function.ssurank

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorRepository
import com.yourssu.ssurank.api.repository.model.projection.ssurank.ProfessorTransporter
import com.yourssu.ssurank.api.response.GetHonorProfessorResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class GetHonorProfessorsFunction(
        private val professorDataAccessor: ProfessorDataAccessor
) {
    fun getHonorProfessors(): Flux<ProfessorTransporter> {
        return professorDataAccessor.getTop10Honors()
    }
}