package com.yourssu.ssurank.api.admin.service.function

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import reactor.core.publisher.Mono

class UpdateProfessorRatingsFunction(
        private val professorDataAccessor: ProfessorDataAccessor,
        private val courseDataAccessor: CourseDataAccessor
) {
    fun updateProfessorRatings(): Mono<Unit> {
        return professorDataAccessor.findAll().flatMap { professor ->
            courseDataAccessor.calculateProfessorRatings(professor.id!!).map {
                professor.rating = it
                professor
            }.flatMap {
                professorDataAccessor.save(it)
            }
        }.collectList().map {}
    }
}