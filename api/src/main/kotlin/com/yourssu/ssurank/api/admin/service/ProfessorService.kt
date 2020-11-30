package com.yourssu.ssurank.api.admin.service

import com.yourssu.ssurank.api.admin.service.function.ReadProfessorFunction
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorRepository
import org.springframework.stereotype.Service

@Service
class ProfessorService(professorRepository: ProfessorRepository, courseRepository: CourseRepository) {

    private val readProssorFunction = ReadProfessorFunction(professorRepository, courseRepository)

    fun readProfessor(){
        readProssorFunction.readExcel()
    }
}