package com.yourssu.ssurank.api.admin.service.function

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Course
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class UpdateCourseRankingFunction(
        private val professorDataAccessor: ProfessorDataAccessor,
        private val courseDataAccessor: CourseDataAccessor
){
    private val getCourseRankingFunction = GetCourseRankingFunction(courseDataAccessor)

    fun updateCourseRanking(): Flux<Course> {
        return courseDataAccessor.findAll().flatMap{
            it.raking = getCourseRankingFunction.getCourseRanking(it)
            courseDataAccessor.save(it)
        }
    }
}