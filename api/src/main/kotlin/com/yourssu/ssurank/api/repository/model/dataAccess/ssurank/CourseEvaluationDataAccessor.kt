package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.CourseEvaluation
import com.yourssu.ssurank.api.repository.model.projection.ssurank.CourseEvaluationTransporter
import com.yourssu.ssurank.api.repository.model.projection.ssurank.MainCourseEvaluationTransporter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class CourseEvaluationDataAccessor(
        @Autowired override var repository: CourseEvaluationRepository
) : DataAccessorAdapterRepository<Int, CourseEvaluation, CourseEvaluationRepository>() {

    fun save(courseEvaluation: CourseEvaluation): CourseEvaluation {
        return repository.save(courseEvaluation)
    }

    fun getMainCourseEvaluations(page: Pageable): List<MainCourseEvaluationTransporter> {
        return repository.findMainCourseEvaluations(page)
    }

    fun getCourseEvaluations(id: Int, page: Pageable): List<CourseEvaluationTransporter> {
        return repository.findAllByCourseId(id, page)
    }

    fun getCourseEvaluation(id: Int): CourseEvaluation {
        return repository.findById(id).get()
    }

    fun countCourseEvaluations(id: Int): Int{
        return repository.countAllByCourseId(id)
    }
}