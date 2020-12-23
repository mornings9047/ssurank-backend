package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.ProfessorEvaluation
import com.yourssu.ssurank.api.repository.model.projection.ssurank.ProfessorEvaluationTransporter
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProfessorEvaluationRepository : ExtendedRepository<Int, ProfessorEvaluation> {
    fun save(professorEvaluation: ProfessorEvaluation): ProfessorEvaluation

    @Query("select student_type as type, created_at as createdAt, content,thumbs_up as thumbsUp, thumbs_down as thumbsDown from Professor_Evaluation pe " +
            "inner join professor_evaluation_list pel on pe.id = pel.professor_evaluation_id " +
            "where pel.professor_id = :id", nativeQuery = true)
    fun findAllByProfessorId(id: Int, page: Pageable): List<ProfessorEvaluationTransporter>
}