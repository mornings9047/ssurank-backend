package com.yourssu.ssurank.api.repository.model.function.ssurank

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.SearchCourseDto
import reactor.core.publisher.Flux

class SearchCourseFunction(
        private val courseDataAccessor: CourseDataAccessor
){
    fun searchCourse(title: String) : Flux<SearchCourseDto> {
        return courseDataAccessor.searchCourse(title).map{
            val professorName = it.professor.name
            SearchCourseDto(it, professorName)
        }
    }
}