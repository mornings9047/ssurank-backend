package com.yourssu.ssurank.api.controller

import com.yourssu.ssurank.api.config.baseUrl
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.CreateCourseEvaluationDto
import com.yourssu.ssurank.api.repository.model.entity.ssurank.StudentType
import com.yourssu.ssurank.api.response.DetailedCourseResponse
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
    fun getDetailedCourse(@PathVariable id: Int): DetailedCourseResponse {
        return DetailedCourseResponse(courseService.getDetailedCourse(id))
    }

    @ApiOperation("강의 한줄평 작성하기")
    @PostMapping("/post/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun createPost(
            @PathVariable id: Int,
            @RequestBody createCourseEvaluationDto: CreateCourseEvaluationDto,
            @RequestParam(required = true) type: StudentType
    ){
        return courseService.createCourseEvaluation(id, createCourseEvaluationDto, type)
    }
}