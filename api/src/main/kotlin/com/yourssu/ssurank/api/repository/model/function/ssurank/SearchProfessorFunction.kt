package com.yourssu.ssurank.api.repository.model.function.ssurank

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.SearchProfessorDto
import com.yourssu.ssurank.api.repository.model.entity.common.Page
import reactor.core.publisher.Flux

class SearchProfessorFunction(
        private val professorDataAccessor: ProfessorDataAccessor
) {
    fun getProfessorsByDept(department: String, page: Int): Flux<SearchProfessorDto> {
        val requestedPage = if (page < 1)
            Page(0, 10, "name")
        else
            Page(page - 1, 10, "name")
        return professorDataAccessor.getProfessorsByDept(department, requestedPage).map {
            SearchProfessorDto(it)
        }
    }

    fun searchProfessor(name: String, page: Int): Flux<SearchProfessorDto> {
        val requestedPage = if (page < 1)
            Page(0, 10, "name")
        else
            Page(page - 1, 10, "name")
        return professorDataAccessor.findAllByProfessorName(name, requestedPage).map {
            SearchProfessorDto(it)
        }
    }

    fun countDepartment(department: String): Int{
        return professorDataAccessor.countDepartment(department)
    }


    fun countProfessor(name: String): Int{
        return professorDataAccessor.countProfessorAllByName(name)
    }
}