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

    @Query("select count(*) from courses c inner join course_professor cp on c.id = cp.professor_id where cp.professor_id = :id", nativeQuery = true)
    fun countCourse(id: Int): Int

    fun getProfessorsByDepartmentOrderByRankingAscRatingDesc(department: String, page: Pageable): List<SearchProfessorTransporter>

//    @Query("select p from Professor p where p.courses.size>=10 order by p.rating desc")
//    fun getProfessorsHavingCoursesOverTen(): List<ProfessorTransporter>

    fun findAllByNameContainsOrderByRankingAscRatingDesc(name: String, page: Pageable): List<SearchProfessorTransporter>
}