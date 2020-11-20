package com.yourssu.ssurank.api.controller

import com.yourssu.ssurank.api.service.ProfessorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/professor")
class ProfessorController@Autowired constructor(val professorService: ProfessorService) {
    @GetMapping("/read")
    @ResponseStatus(HttpStatus.OK)
    fun professorRead(){
        return professorService.readExcel()
    }
}