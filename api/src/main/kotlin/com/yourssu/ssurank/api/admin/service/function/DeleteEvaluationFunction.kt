package com.yourssu.ssurank.api.admin.service.function

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseEvaluationDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorEvaluationDataAccessor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.ReportType

class DeleteEvaluationFunction(
        private val courseEvaluationDataAccessor: CourseEvaluationDataAccessor,
        private val professorEvaluationDataAccessor: ProfessorEvaluationDataAccessor
) {
    fun deleteCourseEvaluation(evaluateId: Int, reportType: String) {
        if (ReportType.COURSE.toString() == reportType) {
            val courseEvaluation = courseEvaluationDataAccessor.getCourseEvaluation(evaluateId)
            courseEvaluation.isDeleted = true
            courseEvaluationDataAccessor.save(courseEvaluation)
        }

        else{
            val professorEvaluation = professorEvaluationDataAccessor.getProfessorEvaluation(evaluateId)
            professorEvaluation.isDeleted = true
            professorEvaluationDataAccessor.save(professorEvaluation)
        }
    }
}