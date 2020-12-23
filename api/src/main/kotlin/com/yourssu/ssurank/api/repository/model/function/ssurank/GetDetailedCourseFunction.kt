package com.yourssu.ssurank.api.repository.model.function.ssurank

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.DetailedCourseDto

class GetDetailedCourseFunction(
        private val courseDataAccessor: CourseDataAccessor
) {
    fun getDetailedCourseFunction(id: Int): DetailedCourseDto {
        val detailedCourseTransporter = courseDataAccessor.findDetailedCourseById(id)
        val historyCourse = courseDataAccessor.getCourseHistoryByCodeAndName(detailedCourseTransporter.code, detailedCourseTransporter.name)
        return DetailedCourseDto(detailedCourseTransporter = detailedCourseTransporter, historyCourses = historyCourse)
    }
}