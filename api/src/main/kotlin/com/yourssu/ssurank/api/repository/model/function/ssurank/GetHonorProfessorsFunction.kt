package com.yourssu.ssurank.api.repository.model.function.ssurank

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import com.yourssu.ssurank.api.repository.model.projection.ssurank.ProfessorTransporter
import reactor.core.publisher.Flux

class GetHonorProfessorsFunction(
        private val professorDataAccessor: ProfessorDataAccessor
) {
    fun getHonorProfessors(): Flux<ProfessorTransporter> {
        return professorDataAccessor.getTop10Honors()
    }
}