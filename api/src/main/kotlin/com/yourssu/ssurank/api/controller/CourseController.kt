package com.yourssu.ssurank.api.controller

import com.yourssu.ssurank.api.config.baseUrl
import com.yourssu.ssurank.api.response.DetailedCourseResponse
import com.yourssu.ssurank.api.response.SearchCourseResponse
import com.yourssu.ssurank.api.service.CourseService
import com.yourssu.ssurank.api.service.ReportService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("$baseUrl/course")
class CourseController(
    val courseService: CourseService,
    val reportService: ReportService
) {
    @ApiOperation("강의 검색하기")
    @GetMapping("/search/{title}/{page}")
    @ResponseStatus(HttpStatus.OK)
    fun searchCourseByTitle(
        @PathVariable title: String,
        @PathVariable page: Int
    ): Mono<SearchCourseResponse> {
        return courseService.searchCourseByTitle(title, page)
            .collectList()
            .map { SearchCourseResponse(it) }
    }

    @ApiOperation("강의 검색결과 수 가져오기")
    @GetMapping("/search/{title}")
    @ResponseStatus(HttpStatus.OK)
    fun countCourseByTitle(
        @PathVariable title: String
    ): Int {
        return courseService.countCourseByTitle(title)
    }

    @ApiOperation("강의 상세보기")
    @GetMapping("/detail/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    fun getDetailedCourse(@PathVariable courseId: Int): DetailedCourseResponse {
        return DetailedCourseResponse(courseService.getDetailedCourse(courseId))
    }
}