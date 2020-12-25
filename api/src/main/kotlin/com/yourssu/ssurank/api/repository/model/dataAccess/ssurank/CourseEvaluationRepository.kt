package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.CourseEvaluation
import com.yourssu.ssurank.api.repository.model.projection.ssurank.CourseEvaluationTransporter
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CourseEvaluationRepository : ExtendedRepository<Int, CourseEvaluation> {
    fun save(courseEvaluation: CourseEvaluation): CourseEvaluation

    @Query("select ce.id as id, student_type as type, create_at as createdAt, content, thumbs_up as thumbsUp, thumbs_down as thumbsDown from course_evaluations ce " +
            "inner join course_evaluation_list cel on ce.id = cel.course_evaluation_id " +
            "where cel.course_id = :id and is_deleted = false", nativeQuery = true)
    fun findAllByCourseId(id: Int, page: Pageable): List<CourseEvaluationTransporter>
}