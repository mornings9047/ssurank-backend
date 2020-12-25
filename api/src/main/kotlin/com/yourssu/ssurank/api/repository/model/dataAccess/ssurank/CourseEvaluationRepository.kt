package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.CourseEvaluation
import com.yourssu.ssurank.api.repository.model.projection.ssurank.CourseEvaluationTransporter
import com.yourssu.ssurank.api.repository.model.projection.ssurank.MainCourseEvaluationTransporter
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CourseEvaluationRepository : ExtendedRepository<Int, CourseEvaluation> {
    fun save(courseEvaluation: CourseEvaluation): CourseEvaluation

    @Query("select ce.id, c.title, p.name, ce.content " +
            "from course_evaluations ce " +
            "inner join course_evaluation_list cel on ce.id = cel.course_evaluation_id " +
            "inner join courses c on cel.course_id = c.id " +
            "inner join course_professor cp on c.id = cp.course_id " +
            "inner join professors p on p.id = cp.professor_id " +
            "where is_deleted = 0 " +
            "order by id desc", nativeQuery = true)
    fun findMainCourseEvaluations(page: Pageable): List<MainCourseEvaluationTransporter>

    @Query("select ce.id as id, student_type as type, create_at as createdAt, content, thumbs_up as thumbsUp, thumbs_down as thumbsDown from course_evaluations ce " +
            "inner join course_evaluation_list cel on ce.id = cel.course_evaluation_id " +
            "where cel.course_id = :id and is_deleted = false", nativeQuery = true)
    fun findAllByCourseId(id: Int, page: Pageable): List<CourseEvaluationTransporter>
}