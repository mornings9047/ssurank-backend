package com.yourssu.ssurank.api.admin.service.function

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor

class UpdateProfessorRatingsAndRankingsFunction(
        private val professorDataAccessor: ProfessorDataAccessor,
        courseDataAccessor: CourseDataAccessor
) {
    private val updateProfessorRatingFunction = UpdateProfessorRatingFunction(courseDataAccessor)
    private val updateProfessorRankingFunction = UpdateProfessorRankingFunction(courseDataAccessor, professorDataAccessor)

    fun updateProfessorRatingsAndGrades() {
        val professors = professorDataAccessor.findAll()
        for (professor in professors)
            updateProfessorRatingFunction.updateProfessorRating(professor)
        for (professor in professors)
            professorDataAccessor.save(professor)
        for (professor in professors)
            updateProfessorRankingFunction.updateProfessorRanking(professor)
        for (professor in professors)
            professorDataAccessor.save(professor)
    }
}