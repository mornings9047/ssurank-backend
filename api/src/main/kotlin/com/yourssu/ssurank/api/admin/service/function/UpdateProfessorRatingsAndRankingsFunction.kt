package com.yourssu.ssurank.api.admin.service.function

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import reactor.core.publisher.Mono

class UpdateProfessorRatingsAndRankingsFunction(
    private val professorDataAccessor: ProfessorDataAccessor, courseDataAccessor: CourseDataAccessor
){
    private val updateProfessorRatingFunction = UpdateProfessorRatingFunction(courseDataAccessor)
    private val updateRankingFunction = UpdateProfessorRankingFunction(courseDataAccessor)

    fun updateProfessorRatingsAndRankingsAndGrades(): Mono<Unit>{
        return professorDataAccessor.findAll().flatMap {
            updateProfessorRatingFunction.updateProfessorRating(it)
        }.flatMap {
            updateRankingFunction.updateProfessorRanking(it)
        }.flatMap {
            professorDataAccessor.save(it)
        }.collectList().map {  }
    }
}