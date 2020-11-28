package com.yourssu.ssurank.api.admin.service

import com.yourssu.ssurank.api.admin.service.function.ReadProfessorFunction
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.course.CourseRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.professor.ProfessorRepository
import org.springframework.stereotype.Service

@Service
class ProfessorService(val professorRepository: ProfessorRepository, val courseRepository: CourseRepository) {

    private val readProssorFunction = ReadProfessorFunction(professorRepository, courseRepository)

    fun readProfessor(){
        readProssorFunction.readExcel()
    }
}