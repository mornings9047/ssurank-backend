package com.yourssu.ssurank.api.admin.service

import com.yourssu.ssurank.api.admin.service.function.ReadCourseFunction
import com.yourssu.ssurank.api.admin.service.function.ReadProfessorFunction
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorRepository
import org.springframework.stereotype.Service

@Service
class AdminCourseService(
        courseRepository: CourseRepository,
        professorRepository: ProfessorRepository
) {
    private val readCourseFunction = ReadCourseFunction(courseRepository, professorRepository)
    private val readProfessorFunction = ReadProfessorFunction(professorRepository, courseRepository)

    fun readCourse() {
        readCourseFunction.readExcel()
        readProfessorFunction.connectCourseAndProfessor()
    }
}