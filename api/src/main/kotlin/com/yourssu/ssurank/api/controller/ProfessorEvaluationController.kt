package com.yourssu.ssurank.api.controller

import com.yourssu.ssurank.api.config.baseUrl
import com.yourssu.ssurank.api.repository.model.entity.ssurank.ReportType
import com.yourssu.ssurank.api.request.ProfessorEvaluationRequest
import com.yourssu.ssurank.api.request.ReportRequest
import com.yourssu.ssurank.api.response.MainProfessorEvaluationResponse
import com.yourssu.ssurank.api.response.ProfessorEvaluationResponse
import com.yourssu.ssurank.api.service.ProfessorService
import com.yourssu.ssurank.api.service.ReportService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("$baseUrl/professor/evaluation")
class ProfessorEvaluationController(
        val professorService: ProfessorService,
        val reportService: ReportService
) {
    @ApiOperation("교수 한줄평 메인 가져오기")
    @GetMapping("/evaluation/main")
    @ResponseStatus(HttpStatus.OK)
    fun getRecentCourseEvaluations(): MainProfessorEvaluationResponse {
        return MainProfessorEvaluationResponse(professorService.getMainCourseEvaluations())
    }

    @ApiOperation("교수 한줄평 작성하기")
    @PostMapping(value = ["/evaluation"], consumes = ["application/json"], produces = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun evaluateProfessor(
            @RequestBody professorEvaluationRequest: ProfessorEvaluationRequest
    ) {
        return professorService.evaluateProfessor(professorEvaluationRequest)
    }

    @ApiOperation("교수 한줄평 최신순 가져오기")
    @GetMapping("/evaluation/recent/{professorId}/{page}")
    @ResponseStatus(HttpStatus.OK)
    fun getRecentProfessorEvaluations(
            @PathVariable professorId: Int,
            @PathVariable page: Int
    ): ProfessorEvaluationResponse {
        return ProfessorEvaluationResponse(professorService.getRecentProfessorEvaluations(professorId, page))
    }

    @ApiOperation("교수 한줄평 추천순 가져오기")
    @GetMapping("/evaluation/recommendation/{professorId}/{page}")
    @ResponseStatus(HttpStatus.OK)
    fun getProfessorEvaluations(
            @PathVariable professorId: Int,
            @PathVariable page: Int
    ): ProfessorEvaluationResponse {
        return ProfessorEvaluationResponse(professorService.getRecommendedProfessorEvaluations(professorId, page))
    }

    @ApiOperation("교수 한줄평 수 가져오기")
    @GetMapping("/evaluation/{professorId}")
    @ResponseStatus(HttpStatus.OK)
    fun countProfessorEvaluations(
            @PathVariable professorId: Int
    ): Int {
        return professorService.countProfessorEvaluations(professorId)
    }

    @ApiOperation("교수 한줄평 신고하기")
    @PostMapping(value = ["/evaluation/report"], consumes = ["application/json"], produces = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun reportEvaluation(
            @RequestBody reportRequest: ReportRequest
    ) {
        reportService.reportEvaluation(reportRequest, ReportType.PROFESSOR)
    }
}