package com.yourssu.ssurank.api.admin.service.function

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Professor
import reactor.core.publisher.Mono

class UpdateProfessorRatingFunction(
        private val courseDataAccessor: CourseDataAccessor
) {
    /*
    fun updateProfessorRating(professor: Professor): Mono<Professor> {
        return courseDataAccessor.calculateProfessorRatings(professor.id!!).map {
            professor.rating = it
            professor
        }
    }
     */
}


