package com.yourssu.ssurank.api.controller

import com.yourssu.ssurank.api.service.CourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/course")
class CourseController @Autowired constructor(val courseService: CourseService){
    @GetMapping("/read")
    @ResponseStatus(HttpStatus.OK)
    fun courseRead(){
        return courseService.readExcel()
    }
}