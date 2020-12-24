package com.yourssu.ssurank.api.admin.service

import com.yourssu.ssurank.api.admin.service.function.DeleteEvaluationFunction
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseEvaluationDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorEvaluationDataAccessor
import org.springframework.stereotype.Service

@Service
class AdminReportService(
        professorEvaluationDataAccessor: ProfessorEvaluationDataAccessor,
        courseEvaluationDataAccessor: CourseEvaluationDataAccessor
) {
    val deleteEvaluationFunction = DeleteEvaluationFunction(courseEvaluationDataAccessor, professorEvaluationDataAccessor)

    fun deleteEvaluation(evaluateId: Int, reportType: String) {
        return deleteEvaluationFunction.deleteCourseEvaluation(evaluateId, reportType)
    }
}