package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Professor
import com.yourssu.ssurank.api.repository.model.projection.ssurank.ProfessorTransporter
import com.yourssu.ssurank.api.repository.model.projection.ssurank.SearchProfessorTransporter
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProfessorRepository : ExtendedRepository<Int, Professor> {
    fun existsByNameAndCollegeAndDepartmentAndPosition(Name: String, College: String, Department: String, Position: String): Boolean

    fun findByNameAndCollegeAndDepartmentAndPosition(Name: String, College: String, Department: String, Position: String): Professor
    /*
    fun getProfessorsByDepartmentOrderByRankingAscRatingDesc(department: String, page: Pageable): List<SearchProfessorTransporter>

    p.course.size >= 10 수정 필요
    @Query("select p from Professor p where p.courses.size>=10 order by p.rating desc")
    fun getProfessorsHavingCoursesOverTen(): List<ProfessorTransporter>
    */
    fun findAllByNameContainsOrderByRankingAscRatingDesc(name: String, page: Pageable): List<SearchProfessorTransporter>
}