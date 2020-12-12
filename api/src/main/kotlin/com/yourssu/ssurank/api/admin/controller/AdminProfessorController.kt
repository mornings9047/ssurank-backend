package com.yourssu.ssurank.api.admin.controller

import com.yourssu.ssurank.api.admin.service.AdminProfessorService
import com.yourssu.ssurank.api.config.baseUrl
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("$baseUrl/admin/professor")
class AdminProfessorController(
        val adminProfessorService: AdminProfessorService
) {
    @GetMapping("/read")
    @ResponseStatus(HttpStatus.OK)
    fun readProfessor() {
        return adminProfessorService.readProfessor()
    }

    /* 랭킹 업데이트 메소드 수정 필요
    @GetMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    fun updateProfessorRatingsAndGrades(): Mono<Unit> {
        return adminProfessorService.updateProfessorRatingsAndGrades()
    }
     */
}