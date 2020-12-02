package com.yourssu.ssurank.api.admin.service.function

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Professor

class GetProfessorRankingFunction(
        private val courseDataAccessor: CourseDataAccessor
) {
    fun getProfessorRanking(professor: Professor): String {
        if (professor.courses!!.size < 5)
            return "U"
        val ratings = courseDataAccessor.getPercentRank(professor.rating)
        return when {
            ratings >= 90 -> "A+"
            ratings >= 80 -> "A0"
            ratings >= 70 -> "A-"
            ratings >= 60 -> "B+"
            ratings >= 40 -> "B0"
            ratings >= 30 -> "B-"
            ratings >= 25 -> "C+"
            ratings >= 20 -> "C0"
            ratings >= 15 -> "C-"
            ratings >= 10 -> "D+"
            ratings >= 5 -> "D0"
            else -> "D-"
        }
    }
}