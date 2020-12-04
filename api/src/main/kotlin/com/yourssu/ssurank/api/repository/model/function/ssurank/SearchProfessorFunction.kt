package com.yourssu.ssurank.api.repository.model.function.ssurank

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.SearchProfessorDto
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import reactor.core.publisher.Flux

class SearchProfessorFunction(
        private val professorDataAccessor: ProfessorDataAccessor
) {
    fun searchProfessor(name: String, page: Int): Flux<SearchProfessorDto> {
        val requestedPage = if (page < 1)
            PageRequest.of(0, 10, Sort.by("name"))
        else
            PageRequest.of(page - 1, 10, Sort.by("name"))
        return professorDataAccessor.findAllByProfessorName(name, requestedPage).map {
            SearchProfessorDto(it)
        }
    }
}