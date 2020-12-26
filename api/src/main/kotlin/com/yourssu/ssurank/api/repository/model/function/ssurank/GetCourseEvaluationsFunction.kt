package com.yourssu.ssurank.api.repository.model.function.ssurank

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseEvaluationDataAccessor
import com.yourssu.ssurank.api.repository.model.projection.ssurank.CourseEvaluationTransporter
import com.yourssu.ssurank.api.repository.model.entity.common.Page
import com.yourssu.ssurank.api.repository.model.projection.ssurank.MainCourseEvaluationTransporter

class GetCourseEvaluationsFunction(
        private val courseEvaluationDataAccessor: CourseEvaluationDataAccessor
) {
    fun getMainCourseEvaluations(): List<MainCourseEvaluationTransporter> {
        val requestedPage = Page(0, 10, "id")
        return courseEvaluationDataAccessor.getMainCourseEvaluations(requestedPage)
    }

    fun getRecentCourseEvaluations(id: Int, page: Int): List<CourseEvaluationTransporter>{
        val requestedPage = if (page <= 1)
            Page(0, 10, "id")
        else
            Page(page - 1, 10, "id")
        return courseEvaluationDataAccessor.getCourseEvaluations(id, requestedPage)
    }

    fun getRecommendedCourseEvaluations(id: Int, page: Int): List<CourseEvaluationTransporter>{
        val requestedPage = if (page <= 1)
            Page(0, 10, "thumbsUp")
        else
            Page(page - 1, 10, "thumbsUp")
        return courseEvaluationDataAccessor.getCourseEvaluations(id, requestedPage)
    }

    fun countCourseEvaluations(id: Int): Int{
        return courseEvaluationDataAccessor.countCourseEvaluations(id)
    }
}