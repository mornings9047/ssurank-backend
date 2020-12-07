package com.yourssu.ssurank.api.admin.service.function

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Course
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Professor

class GetCourseRankingFunction(
        private val courseDataAccessor: CourseDataAccessor,
){
    fun getCourseRanking(course: Course): String {
        val ratings = courseDataAccessor.getProfessorPercentRank(course.rating)
        return when {
            ratings <= 10 -> "A+"
            ratings <= 20 -> "A0"
            ratings <= 30 -> "A-"
            ratings <= 40 -> "B+"
            ratings <= 60 -> "B0"
            ratings <= 70 -> "B-"
            ratings <= 75 -> "C+"
            ratings <= 80 -> "C0"
            ratings <= 85 -> "C-"
            ratings <= 90 -> "D+"
            ratings <= 95 -> "D0"
            else -> "D-"
        }
    }
}