package com.yourssu.ssurank.api.repository.model.function.ssurank

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseEvaluationListRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseEvaluationRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseRepository
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.CreateCourseEvaluationDto
import com.yourssu.ssurank.api.repository.model.entity.ssurank.CourseEvaluation
import com.yourssu.ssurank.api.repository.model.entity.ssurank.CourseEvaluationList
import com.yourssu.ssurank.api.repository.model.entity.ssurank.StudentType
import org.springframework.data.repository.findByIdOrNull

class CreateCourseEvaluationFunction(
        private val courseEvaluationRepository: CourseEvaluationRepository,
        private val courseEvaluationListRepository: CourseEvaluationListRepository,
        private val courseRepository: CourseRepository
) {
    fun createCourseEvaluation(courseId: Int, courseEvaluationDto: CreateCourseEvaluationDto, type : StudentType){
        val course = courseRepository.findByIdOrNull(courseId)
        val courseEvaluation = CourseEvaluation(
                email = courseEvaluationDto.email,
                content = courseEvaluationDto.content,
                year = courseEvaluationDto.year,
                semester = courseEvaluationDto.semester,
                studentType = type,
        )

        courseEvaluationRepository.save(courseEvaluation)
        courseEvaluationListRepository.save(CourseEvaluationList(course = course, courseEvaluation = courseEvaluation))
    }
}