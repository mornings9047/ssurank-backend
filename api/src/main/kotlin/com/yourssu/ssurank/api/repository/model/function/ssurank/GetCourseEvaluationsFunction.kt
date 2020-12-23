package com.yourssu.ssurank.api.repository.model.function.ssurank

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseEvaluationDataAccessor
import com.yourssu.ssurank.api.repository.model.projection.ssurank.CourseEvaluationTransporter
import com.yourssu.ssurank.api.repository.model.entity.common.Page

class GetCourseEvaluationsFunction(
        private val courseEvaluationDataAccessor: CourseEvaluationDataAccessor
) {
    fun getRecentCourseEvaluations(id: Int, page: Int): List<CourseEvaluationTransporter>{
        val requestedPage = Page(page, 10, "id")
        return courseEvaluationDataAccessor.getCourseEvaluations(id, requestedPage)
    }

    fun getRecommendedCourseEvaluations(id: Int, page: Int): List<CourseEvaluationTransporter>{
        val requestedPage = Page(page, 10, "thumbsUp")
        return courseEvaluationDataAccessor.getCourseEvaluations(id, requestedPage)
    }
}