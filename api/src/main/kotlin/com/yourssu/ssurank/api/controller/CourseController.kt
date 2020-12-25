package com.yourssu.ssurank.api.controller

import com.yourssu.ssurank.api.config.baseUrl
import com.yourssu.ssurank.api.repository.model.entity.ssurank.ReportType
import com.yourssu.ssurank.api.request.CourseEvaluationRequest
import com.yourssu.ssurank.api.request.ReportRequest
import com.yourssu.ssurank.api.response.CourseEvaluationResponse
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
    @GetMapping("/detail/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getDetailedCourse(@PathVariable id: Int): DetailedCourseResponse {
        return DetailedCourseResponse(courseService.getDetailedCourse(id))
    }

    @ApiOperation("강의 한줄평 작성하기")
    @PostMapping("/evaluation")
    @ResponseStatus(HttpStatus.CREATED)
    fun evaluationCourse(
            @RequestBody courseEvaluationRequest: CourseEvaluationRequest
    ) {
        return courseService.evaluateCourse(courseEvaluationRequest)
    }

    @ApiOperation("강의 한줄평 수 가져오기")
    @GetMapping("/evaluation/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    fun countCourseEvaluations(
            @PathVariable courseId: Int
    ): Int {
        return courseService.countCourseEvaluations(courseId)
    }

    @ApiOperation("강의 한줄평 최신순 가져오기")
    @GetMapping("/evaluation/recent/{courseId}/{page}")
    @ResponseStatus(HttpStatus.OK)
    fun getRecentCourseEvaluations(
            @PathVariable courseId: Int,
            @PathVariable page: Int
    ): CourseEvaluationResponse {
        return CourseEvaluationResponse(courseService.getRecentCourseEvaluations(courseId, page))
    }

    @ApiOperation("강의 한줄평 추천순 가져오기")
    @GetMapping("/evaluation/recommendation/{courseId}/{page}")
    @ResponseStatus(HttpStatus.OK)
    fun getRecommendedCourseEvaluations(
            @PathVariable courseId: Int,
            @PathVariable page: Int
    ): CourseEvaluationResponse {
        return CourseEvaluationResponse(courseService.getRecommendedCourseEvaluations(courseId, page))
    }

    @ApiOperation("강의 한줄평 신고하기")
    @PostMapping("/evaluation/report")
    @ResponseStatus(HttpStatus.CREATED)
    fun reportEvaluation(
            @RequestBody reportRequest: ReportRequest
    ) {
        reportService.reportEvaluation(reportRequest, ReportType.COURSE)
    }
}