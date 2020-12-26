package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.ProfessorEvaluation
import com.yourssu.ssurank.api.repository.model.projection.ssurank.ProfessorEvaluationTransporter
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProfessorEvaluationRepository : ExtendedRepository<Int, ProfessorEvaluation> {
    fun save(professorEvaluation: ProfessorEvaluation): ProfessorEvaluation

    @Query("select pe.id as id, student_type as type, created_at as createdAt, content,thumbs_up as thumbsUp, thumbs_down as thumbsDown from professor_evaluations pe " +
            "inner join professor_evaluation_list pel on pe.id = pel.professor_evaluation_id " +
            "where pel.professor_id = :id and is_deleted = false", nativeQuery = true)
    fun findAllByProfessorId(id: Int, page: Pageable): List<ProfessorEvaluationTransporter>

    @Query("select count(*) from professor_evaluations pe\n" +
            "inner join professor_evaluation_list pel on pe.id = pel.professor_evaluation_id\n" +
            "where pel.professor_id = :id and is_deleted = false", nativeQuery = true)
    fun countAllByProfessorId(id: Int): Int
}