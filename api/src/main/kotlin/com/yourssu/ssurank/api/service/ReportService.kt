package com.yourssu.ssurank.api.service

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseEvaluationDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorEvaluationDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ReportDataAccessor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.ReportType
import com.yourssu.ssurank.api.repository.model.function.ssurank.ReportEvaluationFunction
import com.yourssu.ssurank.api.request.ReportRequest
import org.springframework.stereotype.Service

@Service
class ReportService(
        professorEvaluationDataAccessor: ProfessorEvaluationDataAccessor,
        courseEvaluationDataAccessor: CourseEvaluationDataAccessor,
        reportDataAccessor: ReportDataAccessor
) {
    private val reportEvaluationFunction = ReportEvaluationFunction(professorEvaluationDataAccessor, courseEvaluationDataAccessor, reportDataAccessor)

    fun reportEvaluation(reportRequest: ReportRequest, reportType: ReportType) {
        reportEvaluationFunction.reportEvaluation(reportRequest, reportType)
    }
}