package com.yourssu.ssurank.api.admin.controller

import com.yourssu.ssurank.api.admin.service.ProfessorService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/admin/professor")
class ProfessorController(
        val professorService: ProfessorService
) {
    @GetMapping("/read")
    @ResponseStatus(HttpStatus.OK)
    fun readProfessor() {
        return professorService.readProfessor()
    }

    @GetMapping("/calculate")
    @ResponseStatus(HttpStatus.OK)
    fun calculateProfessorRatings(): Mono<Unit> {
        return professorService.updateProfessorRatings()
    }
}