package com.yourssu.ssurank.api.admin.service.function

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import reactor.core.publisher.Mono

class UpdateProfessorRatingsAndRankingsFunction(
        private val professorDataAccessor: ProfessorDataAccessor,
        courseDataAccessor: CourseDataAccessor
) {
    private val updateProfessorRatingFunction = UpdateProfessorRatingFunction(courseDataAccessor)
    private val updateProfessorRankingFunction = UpdateProfessorRankingFunction(courseDataAccessor)

    fun updateProfessorRatingsAndGrades(): Mono<Unit> {
        return professorDataAccessor.findAll().flatMap {
            updateProfessorRatingFunction.updateProfessorRating(it)
        }.flatMap {
            updateProfessorRankingFunction.updateProfessorRanking(it)
        }.flatMap {
            professorDataAccessor.save(it)
        }.collectList().map { }
    }
}