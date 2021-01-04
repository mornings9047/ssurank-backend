package com.yourssu.ssurank.api.admin.controller

import com.yourssu.ssurank.api.admin.service.AdminDepartmentService
import com.yourssu.ssurank.api.config.baseUrl
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/$baseUrl/admin/department")
class AdminDepartmentController(
        val adminDepartmentService: AdminDepartmentService
) {
    @GetMapping("/read")
    @ResponseStatus(HttpStatus.OK)
    fun readProfessor() {
        return adminDepartmentService.readDepartment()
    }
}