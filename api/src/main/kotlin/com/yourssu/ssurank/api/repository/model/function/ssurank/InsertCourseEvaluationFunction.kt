package com.yourssu.ssurank.api.repository.model.function.ssurank

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseEvaluationDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseEvaluationListDataAccessor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.CourseEvaluation
import com.yourssu.ssurank.api.repository.model.entity.ssurank.CourseEvaluationList
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Semester
import com.yourssu.ssurank.api.repository.model.entity.ssurank.StudentType
import com.yourssu.ssurank.api.request.CourseEvaluationRequest

class InsertCourseEvaluationFunction(
        private val courseDataAccessor: CourseDataAccessor,
        private val courseEvaluationDataAccessor: CourseEvaluationDataAccessor,
        private val courseEvaluationListDataAccessor: CourseEvaluationListDataAccessor
) {
    fun insertCourseEvaluation(courseEvaluationRequest: CourseEvaluationRequest) {
        val course = courseDataAccessor.findByCourseId(courseEvaluationRequest.courseId)
        val courseEvaluation = courseEvaluationDataAccessor.save(CourseEvaluation(
                email = courseEvaluationRequest.email,
                studentType = when (courseEvaluationRequest.type) {
                    "주전공" -> StudentType.주전공
                    "부전공" -> StudentType.부전공
                    "타전공" -> StudentType.타전공
                    else -> StudentType.복수전공
                },
                content = courseEvaluationRequest.content,
                year = courseEvaluationRequest.year,
                semester = if (courseEvaluationRequest.semester == 0) Semester.FIRST
                else Semester.SECOND
        ))
        val courseEvaluationList = CourseEvaluationList(course = course, courseEvaluation = courseEvaluation)
        courseEvaluationListDataAccessor.save(courseEvaluationList)
    }
}
