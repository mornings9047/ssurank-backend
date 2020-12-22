package com.yourssu.ssurank.api.repository.model.function.ssurank

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.DetailedCourseDto

class GetDetailedCourseFunction(
        private val courseDataAccessor: CourseDataAccessor
) {
    fun getDetailedCourseFunction(id: Int): DetailedCourseDto {
        return courseDataAccessor.findDetailedCourseById(id)
    }
}