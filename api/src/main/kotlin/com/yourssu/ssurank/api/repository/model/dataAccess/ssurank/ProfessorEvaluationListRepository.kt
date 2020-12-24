package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.ProfessorEvaluationList
import org.springframework.stereotype.Repository

@Repository
interface ProfessorEvaluationListRepository : ExtendedRepository<Int, ProfessorEvaluationList>