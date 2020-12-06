package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Course
import com.yourssu.ssurank.api.repository.model.projection.ssurank.SearchCourseTransporter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
open class CourseDataAccessor(
        @Autowired override var repository: CourseRepository
) : DataAccessorAdapterRepository<Int, Course, CourseRepository>() {

    fun calculateProfessorRatings(id: Int): Mono<Float> {
        return monoFromCallableWithScheduler { repository.calculateProfessorRatings(id) }
    }

    fun getPercentRank(rating: Float): Float {
        return repository.getPercentRank(rating)
    }

    fun searchCourse(title: String): Flux<SearchCourseTransporter> {
        return monoFromCallableWithScheduler { repository.searchCourseByTtile(title) }.flatMapMany { Flux.fromIterable(it) }
    }
}