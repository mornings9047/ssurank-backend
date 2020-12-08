package com.yourssu.ssurank.api.admin.service.function

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Course
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Ranking
import reactor.core.publisher.Mono

class GetCourseRankingFunction(
        private val courseDataAccessor: CourseDataAccessor,
) {
    fun getCourseRanking(course: Course): Mono<Course>{
        return courseDataAccessor.getCoursePercentRank(course.rating).map{
            course.ranking = when{
                it <= 30 -> Ranking.A0
                it <= 70 -> Ranking.B0
                it <= 85 -> Ranking.C0
                else -> Ranking.D0
            }
            course
        }
    }
}