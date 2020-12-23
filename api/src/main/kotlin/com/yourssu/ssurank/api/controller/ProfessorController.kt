package com.yourssu.ssurank.api.controller

import com.yourssu.ssurank.api.config.baseUrl
import com.yourssu.ssurank.api.repository.model.projection.ssurank.DetailedProfessorCoursesTransporter
import com.yourssu.ssurank.api.request.ProfessorEvaluationRequest
import com.yourssu.ssurank.api.response.*
import com.yourssu.ssurank.api.service.ProfessorService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("$baseUrl/professor")
class ProfessorController(
        val professorService: ProfessorService
) {
    @ApiOperation("학과 목록 가져오기")
    @GetMapping("/department/lists")
    @ResponseStatus(HttpStatus.OK)
    fun getDepartmentList(): DepartmentListResponse {
        return DepartmentListResponse(professorService.getDepartmentList())
    }

    @ApiOperation("학과별 랭킹 가져오기")
    @GetMapping("/department/{department}/{page}")
    @ResponseStatus(HttpStatus.OK)
    fun getProfessorsByDept(
            @PathVariable department: String,
            @PathVariable page: Int
    ): Mono<SearchProfessorResponse> {
        return professorService.getProfessorsByDept(department, page)
                .collectList()
                .map { SearchProfessorResponse(it) }
    }

    @ApiOperation("명예의 전당 가져오기")
    @GetMapping("/honor")
    @ResponseStatus(HttpStatus.OK)
    fun getHonorProfessors(): Mono<HonorProfessorResponse> {
        return professorService.getHonorProfessors()
                .collectList()
                .map { HonorProfessorResponse(it) }
    }

    @ApiOperation("교수 검색하기")
    @GetMapping("/search/{name}/{page}")
    @ResponseStatus(HttpStatus.OK)
    fun searchProfessor(
            @PathVariable name: String,
            @PathVariable page: Int
    ): Mono<SearchProfessorResponse> {
        return professorService.searchProfessor(name, page)
                .collectList()
                .map { SearchProfessorResponse(it) }
    }

    @ApiOperation("교수 상세보기")
    @GetMapping("/detail/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getDetailedProfessor(
            @PathVariable id: Int
    ): DetailedProfessorResponse {
        return DetailedProfessorResponse(professorService.getDetailedProfessor(id))
    }

    @ApiOperation("교수가 개설한 강의 가져오기")
    @GetMapping("/detail/{id}/{page}")
    @ResponseStatus(HttpStatus.OK)
    fun getProfessorCourses(
            @PathVariable id: Int,
            @PathVariable page: Int
    ): List<DetailedProfessorCoursesTransporter> {
        return professorService.getProfessorCourses(id, page)
    }

    @ApiOperation("교수 한줄평 작성하기")
    @PostMapping("/evaluation")
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
}