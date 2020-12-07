package com.yourssu.ssurank.api.repository.model.function.ssurank

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.SearchCourseDto
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import reactor.core.publisher.Flux

class SearchCourseFunction(
        private val courseDataAccessor: CourseDataAccessor
){
    fun searchCourse(title: String, page: Int) : Flux<SearchCourseDto> {
        val requestedPage = if(page <= 1) PageRequest.of(0, 10, Sort.by("professor.name"))
        else PageRequest.of(page - 1, 10, Sort.by("professor.name"))
        return courseDataAccessor.searchCourse(title,requestedPage).map{
            val professorName = it.professor.name
            SearchCourseDto(it, professorName)
        }
    }
}