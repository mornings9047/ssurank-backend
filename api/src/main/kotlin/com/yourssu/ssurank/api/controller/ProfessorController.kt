package com.yourssu.ssurank.api.controller

import com.yourssu.ssurank.api.config.baseUrl
import com.yourssu.ssurank.api.response.*
import com.yourssu.ssurank.api.service.ProfessorService
import com.yourssu.ssurank.api.service.ReportService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("$baseUrl/professor")
class ProfessorController(
        val professorService: ProfessorService,
        val reportService: ReportService
) {
    @ApiOperation("학과 목록 가져오기")
    @GetMapping("/department/list")
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

    @ApiOperation("학과별 교수 인원 수 가져오기")
    @GetMapping("/department/{department}")
    @ResponseStatus(HttpStatus.OK)
    fun countDepartment(
            @PathVariable department: String
    ): Int {
        return professorService.countDepartment(department)
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

    @ApiOperation("교수 검색 결과 수 가져오기")
    @GetMapping("/search/{name}")
    @ResponseStatus(HttpStatus.OK)
    fun countProfessor(
            @PathVariable name: String
    ): Int {
        return professorService.countProfessor(name)
    }

    @ApiOperation("교수 상세보기")
    @GetMapping("/detail/{professorId}")
    @ResponseStatus(HttpStatus.OK)
    fun getDetailedProfessor(
            @PathVariable professorId: Int
    ): DetailedProfessorResponse {
        return DetailedProfessorResponse(professorService.getDetailedProfessor(professorId))
    }

    @ApiOperation("교수가 개설한 강의 가져오기")
    @GetMapping("/detail/{professorId}/{page}")
    @ResponseStatus(HttpStatus.OK)
    fun getProfessorCourses(
            @PathVariable professorId: Int,
            @PathVariable page: Int
    ): DetailedProfessorCoursesResponse {
        return DetailedProfessorCoursesResponse(professorService.getProfessorCourses(professorId, page))
    }

    @ApiOperation("교수가 개설한 강의 수 가져오기")
    @GetMapping("/courses/{professorId}")
    @ResponseStatus(HttpStatus.OK)
    fun countProfessorCourses(
            @PathVariable professorId: Int
    ): Int {
        return professorService.countProfessorCourses(professorId)
    }
}