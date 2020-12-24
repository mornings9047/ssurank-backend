package com.yourssu.ssurank.api.repository.model.function.ssurank

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import com.yourssu.ssurank.api.repository.model.entity.common.Page
import com.yourssu.ssurank.api.repository.model.projection.ssurank.DetailedProfessorCoursesTransporter

class GetProfessorCoursesFunction(
        private val professorDataAccessor: ProfessorDataAccessor
) {
    fun getProfessorCourses(id: Int, page: Int): List<DetailedProfessorCoursesTransporter> {
        val requestedPage = if (page <= 1)
            Page(0, 5, "rating")
        else
            Page(page - 1, 5, "rating")
        return professorDataAccessor.getCoursesById(id, requestedPage)
    }
}