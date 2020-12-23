package com.yourssu.ssurank.api.repository.model.function.ssurank

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseEvaluationDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorEvaluationDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ReportDataAccessor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Report
import com.yourssu.ssurank.api.repository.model.entity.ssurank.ReportType
import com.yourssu.ssurank.api.request.ReportRequest

class ReportEvaluationFunction(
        private val professorEvaluationDataAccessor: ProfessorEvaluationDataAccessor,
        private val courseEvaluationDataAccessor: CourseEvaluationDataAccessor,
        private val reportDataAccessor: ReportDataAccessor
) {
    fun reportEvaluation(reportRequest: ReportRequest, reportType: ReportType) {
        if (reportType == ReportType.PROFESSOR) {
            val professorEvaluation = professorEvaluationDataAccessor.getProfessorEvaluation(reportRequest.id)
            val report = Report(
                    reportType = reportType,
                    reportCategory = reportRequest.reportCategory,
                    professorEvaluation = professorEvaluation,
                    courseEvaluation = null,
                    content = reportRequest.content
            )
            reportDataAccessor.save(report)
        } else {
            val courseEvaluation = courseEvaluationDataAccessor.getCourseEvaluation(reportRequest.id)
            val report = Report(
                    reportType = reportType,
                    reportCategory = reportRequest.reportCategory,
                    professorEvaluation = null,
                    courseEvaluation = courseEvaluation,
                    content = reportRequest.content
            )
            reportDataAccessor.save(report)
        }
    }
}