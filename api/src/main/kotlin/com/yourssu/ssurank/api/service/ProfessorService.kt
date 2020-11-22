package com.yourssu.ssurank.api.service

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.SimplifiedProfessor
import com.yourssu.ssurank.api.repository.model.function.ssurank.SearchProfessorFunction
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ProfessorService(
        professorDataAccessor: ProfessorDataAccessor
) {
    private val searchProfessorFunction = SearchProfessorFunction(professorDataAccessor)

    fun searchProfessor(professorName: String): Mono<List<SimplifiedProfessor>> {
        return searchProfessorFunction.searchProfessor(professorName)
    }
}