package com.yourssu.ssurank.api.controller

import com.yourssu.ssurank.api.config.baseUrl
import com.yourssu.ssurank.api.repository.model.entity.ssurank.ReportType
import com.yourssu.ssurank.api.request.CourseEvaluationRequest
import com.yourssu.ssurank.api.request.ReportRequest
import com.yourssu.ssurank.api.response.CourseEvaluationResponse
import com.yourssu.ssurank.api.response.MainCourseEvaluationResponse
import com.yourssu.ssurank.api.service.CourseService
import com.yourssu.ssurank.api.service.ReportService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("$baseUrl/course/evaluation")
class CourseEvaluationController(
        val courseService: CourseService,
        val reportService: ReportService
) {
    @ApiOperation("강의 한줄평 메인 가져오기")
    @GetMapping("/evaluation/main")
    @ResponseStatus(HttpStatus.OK)
    fun getRecentCourseEvaluations(): MainCourseEvaluationResponse {
        return MainCourseEvaluationResponse(courseService.getMainCourseEvaluations())
    }

    @ApiOperation("강의 한줄평 작성하기")
    @PostMapping(value = ["/evaluation"], consumes = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun evaluationCourse(
            @RequestBody courseEvaluationRequest: CourseEvaluationRequest
    ) {
        return courseService.evaluateCourse(courseEvaluationRequest)
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

    @ApiOperation("강의 한줄평 수 가져오기")
    @GetMapping("/evaluation/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    fun countCourseEvaluations(
            @PathVariable courseId: Int
    ): Int {
        return courseService.countCourseEvaluations(courseId)
    }

    @ApiOperation("강의 한줄평 신고하기")
    @PostMapping(value = ["/evaluation/report"], consumes = ["application/json"], produces = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun reportEvaluation(
            @RequestBody reportRequest: ReportRequest
    ) {
        reportService.reportEvaluation(reportRequest, ReportType.COURSE)
    }
}