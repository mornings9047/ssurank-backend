package com.yourssu.ssurank.api.admin.service.function

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Professor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Ranking
import reactor.core.publisher.Mono

class UpdateProfessorRankingFunction(
        private val courseDataAccessor: CourseDataAccessor
) {
    fun updateProfessorRanking(professor: Professor): Mono<Professor> {
        if (professor.courses!!.size < 5) {
            professor.ranking = Ranking.U
            return Mono.just(professor)
        }

        return courseDataAccessor.getProfessorPercentRank(professor.rating).map {
            professor.ranking = when {
                it <= 10 -> Ranking.A1
                it <= 20 -> Ranking.A2
                it <= 30 -> Ranking.A3
                it <= 40 -> Ranking.B1
                it <= 60 -> Ranking.B2
                it <= 70 -> Ranking.B3
                it <= 75 -> Ranking.C1
                it <= 80 -> Ranking.C2
                it <= 85 -> Ranking.C3
                it <= 90 -> Ranking.D1
                it <= 95 -> Ranking.D2
                else -> Ranking.D3
            }
            professor
        }
    }
}
