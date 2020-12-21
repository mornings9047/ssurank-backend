package com.yourssu.ssurank.api.repository.model.function.ssurank

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import com.yourssu.ssurank.api.repository.model.entity.common.Page
import com.yourssu.ssurank.api.repository.model.projection.ssurank.DetailedProfessorCoursesTransporter

class GetProfessorCoursesFunction(
        private val professorDataAccessor: ProfessorDataAccessor
) {
    fun getProfessorCourses(name: String, page: Int): List<DetailedProfessorCoursesTransporter> {
        val requestedPage = if (page <= 1)
            Page(0, 3, "year")
        else
            Page(page - 1, 3, "year")
        return professorDataAccessor.getCoursesByName(name, requestedPage)
    }
}