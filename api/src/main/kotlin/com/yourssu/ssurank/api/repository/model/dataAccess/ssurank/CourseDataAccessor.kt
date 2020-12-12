package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.common.Page
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
    /*
    fun calculateProfessorRatings(id: Int): Mono<Float> {
        return monoFromCallableWithScheduler { repository.calculateProfessorRatings(id) }
    }
    */
    fun getProfessorPercentRank(rating: Float): Mono<Float> {
        return monoFromCallableWithScheduler { repository.getProfessorPercentRank(rating) }
    }

    fun getCoursePercentRank(rating: Float): Mono<Float> {
        return monoFromCallableWithScheduler { repository.getCoursePercentRank(rating) }
    }

    fun searchCourseByTitle(title: String, page: Page): Flux<SearchCourseTransporter> {
        return monoFromCallableWithScheduler {
            repository.findAllDistinctByTitleContainsOrderByYearDescSemesterDescRatingDescTitleDesc(title, page)
        }.flatMapMany {
            Flux.fromIterable(it)
        }
    }

    fun findAll(): Flux<Course> {
        return monoFromCallableWithScheduler { repository.findAll() }
                .flatMapMany { Flux.fromIterable(it) }
    }

    fun save(course: Course): Mono<Course> {
        return monoFromCallableWithScheduler { repository.save(course) }
    }
}