package com.yourssu.ssurank.api.admin.service.function

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import reactor.core.publisher.Mono

class UpdateProfessorRatingsAndGradesFunction(
        private val professorDataAccessor: ProfessorDataAccessor,
        private val courseDataAccessor: CourseDataAccessor
) {
    private val getProfessorRankingFunction = GetProfessorRankingFunction(courseDataAccessor)

    fun updateProfessorRatingsAndGrades(): Mono<Unit> {
        return professorDataAccessor.findAll().flatMap { professor ->
            courseDataAccessor.calculateProfessorRatings(professor.id!!).map {
                professor.rating = it
                professor
            }.map {
                it.ranking = getProfessorRankingFunction.getProfessorRanking(it)
                it
            }.flatMap {
                professorDataAccessor.save(it)
            }
        }.collectList().map {}
    }
}