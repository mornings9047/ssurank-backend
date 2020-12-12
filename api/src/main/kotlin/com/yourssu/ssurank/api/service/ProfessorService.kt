package com.yourssu.ssurank.api.service

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.SearchProfessorDto
import com.yourssu.ssurank.api.repository.model.function.ssurank.GetHonorProfessorsFunction
import com.yourssu.ssurank.api.repository.model.function.ssurank.SearchProfessorFunction
import com.yourssu.ssurank.api.repository.model.projection.ssurank.ProfessorTransporter
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class ProfessorService(
        professorDataAccessor: ProfessorDataAccessor
) {
    private val searchProfessorFunction = SearchProfessorFunction(professorDataAccessor)
    private val getHonorsFunction = GetHonorProfessorsFunction(professorDataAccessor)

    /*
    fun getProfessorsByDept(department: String, page: Int): Flux<SearchProfessorDto> {
        return searchProfessorFunction.getProfessorsByDept(department, page)
    }

    fun getHonorProfessors(): Flux<ProfessorTransporter> {
        return getHonorsFunction.getHonorProfessors()
    }
    */
    fun searchProfessor(name: String, page: Int): Flux<SearchProfessorDto> {
        return searchProfessorFunction.searchProfessor(name, page)
    }
}