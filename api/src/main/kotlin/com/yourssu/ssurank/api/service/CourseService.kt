package com.yourssu.ssurank.api.service

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseEvaluationDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseEvaluationListDataAccessor
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.DetailedCourseDto
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.SearchCourseDto
import com.yourssu.ssurank.api.repository.model.function.ssurank.GetCourseEvaluationsFunction
import com.yourssu.ssurank.api.repository.model.function.ssurank.GetDetailedCourseFunction
import com.yourssu.ssurank.api.repository.model.function.ssurank.InsertCourseEvaluationFunction
import com.yourssu.ssurank.api.repository.model.function.ssurank.SearchCourseFunction
import com.yourssu.ssurank.api.repository.model.projection.ssurank.CourseEvaluationTransporter
import com.yourssu.ssurank.api.request.CourseEvaluationRequest
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class CourseService(
        courseDataAccessor: CourseDataAccessor,
        courseEvaluationDataAccessor: CourseEvaluationDataAccessor,
        courseEvaluationListDataAccessor: CourseEvaluationListDataAccessor
) {
    private val searchCourseFunction = SearchCourseFunction(courseDataAccessor)
    private val getDetailedCourseFunction = GetDetailedCourseFunction(courseDataAccessor)
    private val insertCourseEvaluationFunction = InsertCourseEvaluationFunction(courseDataAccessor, courseEvaluationDataAccessor, courseEvaluationListDataAccessor)
    private val getCourseEvaluationsFunction = GetCourseEvaluationsFunction(courseEvaluationDataAccessor)

    fun searchCourseByTitle(title: String, page: Int): Flux<SearchCourseDto> {
        return searchCourseFunction.searchCourseByTitle(title, page)
    }

    fun getDetailedCourse(id: Int): DetailedCourseDto {
        return getDetailedCourseFunction.getDetailedCourseFunction(id)
    }

    fun evaluateCourse(courseEvaluationRequest: CourseEvaluationRequest) {
        return insertCourseEvaluationFunction.insertCourseEvaluation(courseEvaluationRequest)
    }

    fun getRecentCourseEvaluations(id: Int, page: Int) : List<CourseEvaluationTransporter>{
        return getCourseEvaluationsFunction.getRecentCourseEvaluations(id, page)
    }

    fun getRecommendedCourseEvaluations(id: Int, page: Int) : List<CourseEvaluationTransporter>{
        return getCourseEvaluationsFunction.getRecommendedCourseEvaluations(id, page)
    }

    fun countCourseByTitle(title: String) : Int{
        return searchCourseFunction.countCourseByTitle(title)
    }

    fun countCourseEvaluations(id: Int): Int{
        return getCourseEvaluationsFunction.countCourseEvaluations(id)
    }
}