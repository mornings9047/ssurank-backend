package com.yourssu.ssurank.api.controller

import com.yourssu.ssurank.api.config.baseUrl
import com.yourssu.ssurank.api.response.HonorProfessorResponse
import com.yourssu.ssurank.api.response.SearchProfessorResponse
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
//    @ApiOperation("학과별 랭킹 가져오기")
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    fun getDeptRanking() {
//
//    }

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
}