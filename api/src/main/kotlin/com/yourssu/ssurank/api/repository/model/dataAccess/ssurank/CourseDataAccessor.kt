package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Course
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
open class CourseDataAccessor(
        @Autowired override var repository: CourseRepository
) : DataAccessorAdapterRepository<Int, Course, CourseRepository>() {

    fun calculateProfessorRatings(id: Int): Mono<Float> {
        return monoFromCallableWithScheduler { repository.calculateProfessorRatings(id) }
    }

    fun getPercentRank(rating: Float): Mono<Float> {
        return monoFromCallableWithScheduler { repository.getPercentRank(rating) }
    }
}