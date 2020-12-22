package com.yourssu.ssurank.api.repository.model.function.ssurank

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.GetHistoryCourseDto

class GetCourseHistoryFunction(
        private val courseDataAccessor: CourseDataAccessor
) {
    fun getCourseHistory(code: String, name: String): List<GetHistoryCourseDto> {
        return courseDataAccessor.getCourseHistoryByCodeAndName(code, name)
    }
}