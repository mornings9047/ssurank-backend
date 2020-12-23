package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.ProfessorEvaluation
import com.yourssu.ssurank.api.repository.model.projection.ssurank.ProfessorEvaluationTransporter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class ProfessorEvaluationDataAccessor(
        @Autowired override var repository: ProfessorEvaluationRepository
) : DataAccessorAdapterRepository<Int, ProfessorEvaluation, ProfessorEvaluationRepository>() {
    fun save(professorEvaluation: ProfessorEvaluation): ProfessorEvaluation {
        return repository.save(professorEvaluation)
    }

    fun getProfessorEvaluations(id: Int, page: Pageable): List<ProfessorEvaluationTransporter> {
        return repository.findAllByProfessorId(id, page)
    }
}