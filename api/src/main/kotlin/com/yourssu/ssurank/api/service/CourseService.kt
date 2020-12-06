package com.yourssu.ssurank.api.service

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseRepository
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.SearchCourseDto
import com.yourssu.ssurank.api.repository.model.function.ssurank.SearchCourseFunction
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class CourseService(
        courseDataAccessor : CourseDataAccessor
){
    private val searchCourseFunction = SearchCourseFunction(courseDataAccessor)

    fun searchCourse(title: String): Flux<SearchCourseDto> {
        return searchCourseFunction.searchCourse(title)
    }
}