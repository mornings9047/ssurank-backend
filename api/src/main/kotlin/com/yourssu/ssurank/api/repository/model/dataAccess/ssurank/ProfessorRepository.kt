package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Professor
import com.yourssu.ssurank.api.repository.model.projection.ssurank.ProfessorTransporter
import com.yourssu.ssurank.api.repository.model.projection.ssurank.SearchProfessorTransporter
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProfessorRepository : ExtendedRepository<Int, Professor> {
    fun existsByNameAndCollegeAndDepartmentAndPosition(name: String, college: String, department: String, position: String): Boolean
    fun findByNameAndCollegeAndDepartmentAndPosition(name: String, college: String, department: String, position: String): Professor

    fun getProfessorsByDepartmentOrderByRatingDesc(department: String, page: Pageable): List<SearchProfessorTransporter>

    @Query("select p from Professor p where p.courses.size>=10 order by p.rating desc")
    fun getProfessorsHavingCoursesOverTen(): List<ProfessorTransporter>

    fun findAllByNameContainsOrderByRatingDesc(name: String, page: Pageable): List<SearchProfessorTransporter>
}