package com.yourssu.ssurank.api.service

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseEvaluationListRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseEvaluationRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseRepository
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.CreateCourseEvaluationDto
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.DetailedCourseDto
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.SearchCourseDto
import com.yourssu.ssurank.api.repository.model.entity.ssurank.StudentType
import com.yourssu.ssurank.api.repository.model.function.ssurank.CreateCourseEvaluationFunction
import com.yourssu.ssurank.api.repository.model.function.ssurank.GetDetailedCourseFunction
import com.yourssu.ssurank.api.repository.model.function.ssurank.SearchCourseFunction
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class CourseService(
        courseDataAccessor: CourseDataAccessor,
        private val courseEvaluationRepository: CourseEvaluationRepository,
        private val courseEvaluationListRepository: CourseEvaluationListRepository,
        private val courseRepository: CourseRepository
) {
    private val searchCourseFunction = SearchCourseFunction(courseDataAccessor)
    private val getDetailedCourseFunction = GetDetailedCourseFunction(courseDataAccessor)
    private val createCourseEvaluationFunction = CreateCourseEvaluationFunction(courseEvaluationRepository, courseEvaluationListRepository, courseRepository)

    fun searchCourseByTitle(title: String, page: Int): Flux<SearchCourseDto> {
        return searchCourseFunction.searchCourseByTitle(title, page)
    }

    fun getDetailedCourse(id: Int): DetailedCourseDto {
        return getDetailedCourseFunction.getDetailedCourseFunction(id)
    }

    fun createCourseEvaluation(id: Int, createCourseEvaluationDto:CreateCourseEvaluationDto, type: StudentType){
        return createCourseEvaluationFunction.createCourseEvaluation(id, createCourseEvaluationDto, type)
    }

}