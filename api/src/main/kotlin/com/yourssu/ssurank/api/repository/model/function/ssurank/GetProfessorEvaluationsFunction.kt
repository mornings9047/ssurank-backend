package com.yourssu.ssurank.api.repository.model.function.ssurank

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorEvaluationDataAccessor
import com.yourssu.ssurank.api.repository.model.entity.common.Page
import com.yourssu.ssurank.api.repository.model.projection.ssurank.ProfessorEvaluationTransporter

class GetProfessorEvaluationsFunction(
        private val professorEvaluationDataAccessor: ProfessorEvaluationDataAccessor
) {
    fun getRecentProfessorEvaluations(id: Int, page: Int): List<ProfessorEvaluationTransporter> {
        val requestedPage = Page(page, 10, "id")
        return professorEvaluationDataAccessor.getProfessorEvaluations(id, requestedPage)
    }

    fun getRecommendedProfessorEvaluations(id: Int, page: Int): List<ProfessorEvaluationTransporter> {
        val requestedPage = Page(page, 10, "thumbsUp")
        return professorEvaluationDataAccessor.getProfessorEvaluations(id, requestedPage)
    }
}