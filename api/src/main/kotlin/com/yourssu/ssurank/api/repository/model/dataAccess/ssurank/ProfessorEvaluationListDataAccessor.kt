package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.ProfessorEvaluationList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class ProfessorEvaluationListDataAccessor(
        @Autowired override var repository: ProfessorEvaluationListRepository
) : DataAccessorAdapterRepository<Int, ProfessorEvaluationList, ProfessorEvaluationListRepository>() {
    fun save(professorEvaluationList: ProfessorEvaluationList) {
        repository.save(professorEvaluationList)
    }
}