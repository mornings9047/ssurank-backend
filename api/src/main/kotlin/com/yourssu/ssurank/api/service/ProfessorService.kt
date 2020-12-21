package com.yourssu.ssurank.api.service

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.DetailedProfessorDto
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.SearchProfessorDto
import com.yourssu.ssurank.api.repository.model.function.ssurank.GetDetailedProfessorFunction
import com.yourssu.ssurank.api.repository.model.function.ssurank.GetHonorProfessorsFunction
import com.yourssu.ssurank.api.repository.model.function.ssurank.GetProfessorCoursesFunction
import com.yourssu.ssurank.api.repository.model.function.ssurank.SearchProfessorFunction
import com.yourssu.ssurank.api.repository.model.projection.ssurank.DetailedProfessorCoursesTransporter
import com.yourssu.ssurank.api.repository.model.projection.ssurank.ProfessorTransporter
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class ProfessorService(
        professorDataAccessor: ProfessorDataAccessor
) {
    private val searchProfessorFunction = SearchProfessorFunction(professorDataAccessor)
    private val getHonorsFunction = GetHonorProfessorsFunction(professorDataAccessor)
    private val getDetailedProfessorFunction = GetDetailedProfessorFunction(professorDataAccessor)
    private val getProfessorCoursesFunction = GetProfessorCoursesFunction(professorDataAccessor)

    fun getProfessorsByDept(department: String, page: Int): Flux<SearchProfessorDto> {
        return searchProfessorFunction.getProfessorsByDept(department, page)
    }

    fun getHonorProfessors(): Flux<ProfessorTransporter> {
        return getHonorsFunction.getHonorProfessors()
    }

    fun searchProfessor(name: String, page: Int): Flux<SearchProfessorDto> {
        return searchProfessorFunction.searchProfessor(name, page)
    }

    fun getDetailedProfessor(name: String): DetailedProfessorDto {
        return getDetailedProfessorFunction.getDetailedProfessor(name)
    }

    fun getProfessorCourses(name: String, page: Int): List<DetailedProfessorCoursesTransporter> {
        return getProfessorCoursesFunction.getProfessorCourses(name, page)
    }
}