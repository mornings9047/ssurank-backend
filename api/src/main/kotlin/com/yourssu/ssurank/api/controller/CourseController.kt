package com.yourssu.ssurank.api.controller

import com.yourssu.ssurank.api.config.baseUrl
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.SearchCourseDto
import com.yourssu.ssurank.api.response.SearchCourseResponse
import com.yourssu.ssurank.api.service.CourseService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("$baseUrl/course")
class CourseController(
        val courseService: CourseService
){
    @ApiOperation("강의 검색하기")
    @GetMapping("/search/{title}/{page}")
    @ResponseStatus(HttpStatus.OK)
    fun searchCourse(
            @PathVariable title:String,
            @PathVariable page: Int
    ): Mono<SearchCourseResponse> {
        return courseService.searchCourse(title, page)
                .collectList()
                .map { SearchCourseResponse(it) }
    }
}