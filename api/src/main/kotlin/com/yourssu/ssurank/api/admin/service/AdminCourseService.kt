package com.yourssu.ssurank.api.admin.service

import com.yourssu.ssurank.api.admin.service.function.ReadCourseFunction
import com.yourssu.ssurank.api.admin.service.function.UpdateCourseRankingFunction
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseProfessorRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AdminCourseService(
        courseRepository: CourseRepository,
        professorRepository: ProfessorRepository,
        courseProfessorRepository: CourseProfessorRepository,
        courseDataAccessor: CourseDataAccessor,
) {
    private val readCourseFunction = ReadCourseFunction(courseRepository, professorRepository, courseProfessorRepository)
    private val updateCourseRankingFunction = UpdateCourseRankingFunction(courseDataAccessor)

    fun readCourse() {
        return readCourseFunction.readExcel()
    }

    fun updateCourseRanking(): Mono<Unit> {
        return updateCourseRankingFunction.updateCourseRanking()
    }
}