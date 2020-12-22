package com.yourssu.ssurank.api.controller

import com.yourssu.ssurank.api.config.baseUrl
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

    @ApiOperation("강의 상세보기")
    @GetMapping("/detail/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getDetailedCourse(@PathVariable id: Int){
        return courseService.getDetailedCourse(id)
    }

    @ApiOperation("학기별 강의 평가")
    @GetMapping("/history/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getHistoryCourse(@PathVariable id: Int){

    }

}