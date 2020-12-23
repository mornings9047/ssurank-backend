package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.CourseEvaluation
import com.yourssu.ssurank.api.repository.model.projection.ssurank.CourseEvaluationTransporter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class CourseEvaluationDataAccessor(
        @Autowired override var repository: CourseEvaluationRepository
) : DataAccessorAdapterRepository<Int, CourseEvaluation, CourseEvaluationRepository>(){

    fun save(courseEvaluation: CourseEvaluation) : CourseEvaluation{
        return repository.save(courseEvaluation)
    }

    fun getCourseEvaluations(id: Int, page: Pageable): List<CourseEvaluationTransporter>{
        return repository.findAllByCourseId(id, page)
    }

}