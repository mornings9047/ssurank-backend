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

    fun searchCourseByTitle(title: String, page: Int): Flux<SearchCourseDto> {
        return searchCourseFunction.searchCourseByTitle(title, page)
    }

    fun searchCourseByTitleAndProfessorId(title: String, id: Int, page: Int): Flux<SearchCourseDto> {
        return searchCourseFunction.searchCourseByTitleAndProfessorId(title, id, page)
    }
}