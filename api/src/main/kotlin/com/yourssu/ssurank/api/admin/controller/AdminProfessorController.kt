package com.yourssu.ssurank.api.admin.controller

import com.yourssu.ssurank.api.admin.service.AdminProfessorService
import com.yourssu.ssurank.api.config.baseUrl
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

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

    @GetMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    fun updateProfessorRatingsAndGrades() {
        return adminProfessorService.updateProfessorRatingsAndGrades()
    }
}