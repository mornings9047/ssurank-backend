package com.yourssu.ssurank.api.service

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorRepository
import com.yourssu.ssurank.api.repository.model.entity.ssurank.SimplifiedProfessor
import com.yourssu.ssurank.api.repository.model.function.ssurank.GetHonorProfessorsFunction
import com.yourssu.ssurank.api.repository.model.function.ssurank.SearchProfessorFunction
import com.yourssu.ssurank.api.repository.model.projection.ssurank.ProfessorTransporter
import com.yourssu.ssurank.api.response.GetHonorProfessorResponse
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ProfessorService(
        professorDataAccessor: ProfessorDataAccessor
) {
    private val searchProfessorFunction = SearchProfessorFunction(professorDataAccessor)
    private val getHonorsFunction = GetHonorProfessorsFunction(professorDataAccessor)

    fun getHonorProfessors(): Flux<ProfessorTransporter> {
        return getHonorsFunction.getHonorProfessors()
    }

    fun searchProfessor(professorName: String): Mono<List<SimplifiedProfessor>> {
        return searchProfessorFunction.searchProfessor(professorName)
    }
}