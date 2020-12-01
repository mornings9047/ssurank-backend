package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Course
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Professor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : ExtendedRepository<Int, Course> {
    fun existsByCode(Code: String): Boolean
    fun findByCodeContains(Code: String): List<Course>
    fun findByTitleContains(Title: String): List<Course>
    fun findByProfessor(Professor: Professor): List<Course>

    @Query("select avg(c.rating) from Course c where c.professor.id = :id")
    fun calculateProfessorRatings(@Param("id") id: Int): Float
}