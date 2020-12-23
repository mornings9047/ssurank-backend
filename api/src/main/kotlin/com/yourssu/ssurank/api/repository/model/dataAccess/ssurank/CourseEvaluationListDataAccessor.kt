package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.CourseEvaluationList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class CourseEvaluationListDataAccessor(
        @Autowired override var repository: CourseEvaluationListRepository
) : DataAccessorAdapterRepository<Int, CourseEvaluationList, CourseEvaluationListRepository>() {
    fun save(courseEvaluationList: CourseEvaluationList) {
        repository.save(courseEvaluationList)
    }
}