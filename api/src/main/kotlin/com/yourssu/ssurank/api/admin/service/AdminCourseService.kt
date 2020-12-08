package com.yourssu.ssurank.api.admin.service

import com.yourssu.ssurank.api.admin.service.function.ReadCourseFunction
import com.yourssu.ssurank.api.admin.service.function.ReadProfessorFunction
import com.yourssu.ssurank.api.admin.service.function.UpdateCourseRankingFunction
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorRepository
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Course
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class AdminCourseService(
        courseRepository: CourseRepository,
        professorRepository: ProfessorRepository,
        courseDataAccessor: CourseDataAccessor,
        professorDataAccessor: ProfessorDataAccessor
) {
    private val readCourseFunction = ReadCourseFunction(courseRepository, professorRepository)
    private val readProfessorFunction = ReadProfessorFunction(professorRepository, courseRepository)
    private val updateCourseRankingFunction = UpdateCourseRankingFunction(courseDataAccessor)

    fun readCourse() {
        readCourseFunction.readExcel()
        readProfessorFunction.connectCourseAndProfessor()
    }

    fun updateCourseRanking(): Mono<Unit> {
        return updateCourseRankingFunction.updateCourseRanking()
    }
}