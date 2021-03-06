package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.ProfessorEvaluation
import com.yourssu.ssurank.api.repository.model.projection.ssurank.MainProfessorEvaluationTransporter
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

    fun getMainProfessorEvaluations(page: Pageable): List<MainProfessorEvaluationTransporter> {
        return repository.findMainCourseEvaluations(page)
    }

    fun getProfessorEvaluations(id: Int, page: Pageable): List<ProfessorEvaluationTransporter> {
        return repository.findAllByProfessorId(id, page)
    }

    fun getProfessorEvaluation(id: Int): ProfessorEvaluation {
        return repository.findById(id).get()
    }

    fun countProfessorEvaluations(id: Int): Int{
        return repository.countAllByProfessorId(id)
    }
}