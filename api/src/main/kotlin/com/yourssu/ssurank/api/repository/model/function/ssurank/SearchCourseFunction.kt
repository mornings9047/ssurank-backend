package com.yourssu.ssurank.api.repository.model.function.ssurank

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.SearchCourseDto
import com.yourssu.ssurank.api.repository.model.entity.common.Page
import reactor.core.publisher.Flux

class SearchCourseFunction(
        private val courseDataAccessor: CourseDataAccessor
) {
    fun searchCourseByTitle(title: String, page: Int): Flux<SearchCourseDto> {
        val requestedPage = if (page <= 1)
            Page(0, 10, "title")
        else
            Page(page - 1, 10, "title")
        return courseDataAccessor.searchCourseByTitle(title, requestedPage).map {
            SearchCourseDto(it)
        }
    }
}