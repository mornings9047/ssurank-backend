package com.yourssu.ssurank.api.admin.service.function

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Professor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Ranking

class UpdateProfessorRankingFunction(
        private val courseDataAccessor: CourseDataAccessor,
        private val professorDataAccessor: ProfessorDataAccessor
) {
    fun updateProfessorRanking(professor: Professor) {
        if (professorDataAccessor.countCourse(professor) < 5)
            return
        val rating = courseDataAccessor.getProfessorPercentRank(professor.rating)
        professor.ranking = when {
            rating <= 10 -> Ranking.A1
            rating <= 20 -> Ranking.A2
            rating <= 30 -> Ranking.A3
            rating <= 40 -> Ranking.B1
            rating <= 60 -> Ranking.B2
            rating <= 70 -> Ranking.B3
            rating <= 75 -> Ranking.C1
            rating <= 80 -> Ranking.C2
            rating <= 85 -> Ranking.C3
            rating <= 90 -> Ranking.D1
            rating <= 95 -> Ranking.D2
            else -> Ranking.D3
        }
    }
}