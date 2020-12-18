package com.yourssu.ssurank.api.admin.service.function

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Professor

class UpdateProfessorRatingFunction(
        private val courseDataAccessor: CourseDataAccessor
) {
    fun updateProfessorRating(professor: Professor) {
        professor.rating = courseDataAccessor.calculateProfessorRatings(professor.id!!)
    }
}


