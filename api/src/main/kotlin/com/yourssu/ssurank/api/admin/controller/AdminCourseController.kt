package com.yourssu.ssurank.api.admin.controller

import com.yourssu.ssurank.api.admin.service.AdminCourseService
import com.yourssu.ssurank.api.config.baseUrl
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("$baseUrl/admin/course")
class AdminCourseController(val adminCourseService: AdminCourseService) {

    @GetMapping("/read")
    @ResponseStatus(HttpStatus.OK)
    fun readCourse() {
        adminCourseService.readCourse()
    }
}