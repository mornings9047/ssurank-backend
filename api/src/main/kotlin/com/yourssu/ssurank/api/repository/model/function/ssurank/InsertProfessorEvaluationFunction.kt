package com.yourssu.ssurank.api.repository.model.function.ssurank

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorEvaluationDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorEvaluationListDataAccessor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.ProfessorEvaluation
import com.yourssu.ssurank.api.repository.model.entity.ssurank.ProfessorEvaluationList
import com.yourssu.ssurank.api.repository.model.entity.ssurank.StudentType
import com.yourssu.ssurank.api.request.ProfessorEvaluationRequest

class InsertProfessorEvaluationFunction(
        private val professorDataAccessor: ProfessorDataAccessor,
        private val professorEvaluationDataAccessor: ProfessorEvaluationDataAccessor,
        private val professorEvaluationListDataAccessor: ProfessorEvaluationListDataAccessor
) {
    fun insertProfessorEvaluation(professorEvaluationRequest: ProfessorEvaluationRequest) {
        val professor = professorDataAccessor.findByProfessorId(professorEvaluationRequest.professorId)
        val professorEvaluation = professorEvaluationDataAccessor.save(ProfessorEvaluation(
                email = professorEvaluationRequest.email,
                studentType = when (professorEvaluationRequest.type) {
                    "주전공" -> StudentType.주전공
                    "부전공" -> StudentType.부전공
                    "타전공" -> StudentType.타전공
                    else -> StudentType.복수전공
                },
                content = professorEvaluationRequest.content,
        ))
        val professorEvaluationList = ProfessorEvaluationList(professor = professor, professorEvaluation = professorEvaluation)
        professorEvaluationListDataAccessor.save(professorEvaluationList)
    }
}