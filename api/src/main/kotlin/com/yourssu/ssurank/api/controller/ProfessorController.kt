package com.yourssu.ssurank.api.controller

import com.yourssu.ssurank.api.config.baseUrl
import com.yourssu.ssurank.api.response.GetProfessorsResponse
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
    @ApiOperation("학과별 랭킹 가져오기")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getDeptRanking() {

    }

    @ApiOperation("명예의 전당 가져오기")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getHOF() {
        // List of (Rank, professorName, profDept, profTitle)
    }

    @ApiOperation("교수 검색하기")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun searchProfessor(
            @PathVariable professorName: String
    ): Mono<GetProfessorsResponse> {
        return professorService.searchProfessor(professorName).map {
            GetProfessorsResponse(it)
        }
    }
}

