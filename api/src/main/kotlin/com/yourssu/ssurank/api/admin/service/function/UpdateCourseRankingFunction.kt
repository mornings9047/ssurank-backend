package com.yourssu.ssurank.api.admin.service.function

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import reactor.core.publisher.Mono

class UpdateCourseRankingFunction(
        private val courseDataAccessor: CourseDataAccessor
) {
    private val getCourseRankingFunction = GetCourseRankingFunction(courseDataAccessor)

    fun updateCourseRanking(): Mono<Unit> {
        return courseDataAccessor.findAll().flatMap {
            getCourseRankingFunction.getCourseRanking(it)
        }.flatMap {
            courseDataAccessor.save(it)
        }.collectList().map {}
    }
}
